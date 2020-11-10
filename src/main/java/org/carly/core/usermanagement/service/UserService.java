package org.carly.core.usermanagement.service;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.AddressRequest;
import org.carly.api.rest.request.LoginRequest;
import org.carly.api.rest.request.SignupCompanyRequest;
import org.carly.api.rest.request.SignupRequest;
import org.carly.api.rest.response.JwtTokenResponse;
import org.carly.api.rest.response.MessageResponse;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.customermanagement.model.Customer;
import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.security.model.LoggedUser;
import org.carly.core.security.service.JwtUtils;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.model.Address;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.mapper.UserMapper;
import org.carly.core.usermanagement.model.Gender;
import org.carly.core.usermanagement.model.OnRegistrationCompleteEvent;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.VerificationToken;
import org.carly.core.usermanagement.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.carly.core.security.model.UserRole.CHANGE_PASSWORD_PRIVILEGE;
import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service("userDetailsService")
public class UserService {

    @Resource(name = "tokenBlackListService")
    private TokenBlackListService blackListService;

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final MessageSource messageSource;
    private final UserMapper userMapper;
    private final TimeService timeService;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final LoggedUserProvider loggedUserProvider;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository,
                       TokenService tokenService,
                       MessageSource messageSource,
                       UserMapper userMapper,
                       TimeService timeService,
                       ApplicationEventPublisher eventPublisher,
                       PasswordEncoder passwordEncoder,
                       MailService mailService,
                       LoggedUserProvider loggedUserProvider,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder encoder,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
        this.timeService = timeService;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.loggedUserProvider = loggedUserProvider;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void clearBlackList() {
        blackListService.clearBlackList();
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        LoggedUser userDetails = (LoggedUser) authentication.getPrincipal();
        if (Boolean.FALSE.equals(userDetails.getEnabled())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Account is not active"));
        }

        return ResponseEntity.ok(new JwtTokenResponse(jwt));
    }

    public ResponseEntity<?> register(SignupRequest signUpRequest, WebRequest webRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(
                new Customer
                        (
                                signUpRequest.getFirstName(),
                                signUpRequest.getLastName(),
                                Gender.valueOf(signUpRequest.getGender())
                        ),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                encoder.encode(signUpRequest.getPassword()));

        user.setRoles(List.of(CarlyGrantedAuthority.of("CARLY_CUSTOMER")));
        user.setEnabled(Boolean.FALSE);
        userRepository.save(user);
        publishRegistrationUser(user, webRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    public ResponseEntity<?> registerCompany(SignupCompanyRequest signupCompanyRequest, WebRequest webRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signupCompanyRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Address address = null;
        if (signupCompanyRequest.getAddressRequest() != null) {
            address = new Address(
                    signupCompanyRequest.getAddressRequest().getStreet(),
                    signupCompanyRequest.getAddressRequest().getNumber(),
                    signupCompanyRequest.getAddressRequest().getFlat(),
                    signupCompanyRequest.getAddressRequest().getTown(),
                    signupCompanyRequest.getAddressRequest().getZipCode(),
                    signupCompanyRequest.getAddressRequest().getCountry());
        }
        User company = new User
                (
                        new Company(signupCompanyRequest.getCompanyName()),
                        signupCompanyRequest.getEmail(),
                        signupCompanyRequest.getPhone(),
                        encoder.encode(signupCompanyRequest.getPassword())
                );
        company.setAddress(address);
        company.setRoles(List.of(CarlyGrantedAuthority.of("CARLY_COMPANY")));
        company.setEnabled(Boolean.FALSE);
        userRepository.save(company);
        publishRegistrationUser(company, webRequest);

        return ResponseEntity.ok(new MessageResponse("Company registered successfully"));
    }

    public void publishRegistrationUser(User user, WebRequest request) {
        OnRegistrationCompleteEvent onRegistrationCompleteEvent;
        try {
            String appUrl = request.getContextPath();
            onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(appUrl, request.getLocale(), user);
            eventPublisher.publishEvent(onRegistrationCompleteEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String confirmRegistration(WebRequest request, String token) {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return messageSource.getMessage("auth.message.clicked", null, locale);
        }
        User user = verificationToken.getUser();
        LocalDateTime clickedDateTime = timeService.getLocalDateTime();

        if (clickedDateTime.isAfter(verificationToken.getExpiryDate()) || clickedDateTime.isEqual(verificationToken.getExpiryDate())) {
            return messageSource.getMessage("auth.message.expired", null, locale);
        }
        user.setEnabled(true);
        userRepository.save(user);
        tokenService.removeToken(verificationToken);
        return messageSource.getMessage("auth.message.complete", null, locale);
    }

    public ResponseEntity<String> resetUserPassword(HttpServletRequest request, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        String verificationToken = tokenService.createVerificationToken(user);
        String url = "http://localhost:8080/user/changePassowrd?id=" + user.getId() + "&token=" + verificationToken;
        mailService.resetPassword(request, url, user);
        return ResponseEntity.ok().build();
    }

    public String changePassword(String id, String token, WebRequest request) {
        User user = userRepository.findById(new ObjectId(id)).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return messageSource.getMessage("auth.message.clicked", null, request.getLocale());
        }
        User userFromVerification = verificationToken.getUser();
        if (userFromVerification.getId().equals(user.getId())) {
            LocalDateTime clickedDateTime = timeService.getLocalDateTime();
            if (clickedDateTime.isAfter(verificationToken.getExpiryDate()) || clickedDateTime.isEqual(verificationToken.getExpiryDate())) {
                return messageSource.getMessage("auth.message.expired", null, request.getLocale());
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken
                    (user, null, List.of(new SimpleGrantedAuthority(CHANGE_PASSWORD_PRIVILEGE.name())));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return messageSource.getMessage("auth.message.complete", null, request.getLocale());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build().toString();
    }

    public ResponseEntity saveNewPassword(String password) {
        User user = userRepository.findById(loggedUserProvider.provideUserDetail().getId()).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (user.getRoles().contains(CarlyGrantedAuthority.of(CHANGE_PASSWORD_PRIVILEGE.name()))) {
            user.setPassword(passwordEncoder.encode(password));
            user.getRoles().removeIf(role -> role.getUserRole() == CHANGE_PASSWORD_PRIVILEGE);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity<AddressRequest> addAddress(ObjectId userId, AddressRequest newAddress) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (user.getAddress() != null) {
            Address oldAddress = user.getAddress();
            oldAddress.setModifiedAt(timeService.getLocalDate());

            Address address = userMapper.mapAddressToDomain(newAddress);
            address.setCreatedAt(timeService.getLocalDate());
            user.setAddress(address);
            if (user.getAddressHistory() == null) {
                user.setAddressHistory(new ArrayList<>());
                user.getAddressHistory().add(oldAddress);
            } else {
                user.getAddressHistory().add(oldAddress);
            }
            userRepository.save(user);
            return ResponseEntity.ok().body(newAddress);
        } else {
            Address address = userMapper.mapAddressToDomain(newAddress);
            address.setCreatedAt(timeService.getLocalDate());
            user.setAddress(address);
            userRepository.save(user);
            return ResponseEntity.ok().body(newAddress);
        }
    }

    public ResponseEntity<String> refreshToken(String bearerToken) {
        String token = jwtUtils.resolveToken(bearerToken).stream().findFirst().orElse(null);
        if (token == null || token.isEmpty()) {
            throw new AuthenticationServiceException("Invalid token");
        }
        String newToken = jwtUtils.refreshToken(token);
        if (newToken == null) {
            return ResponseEntity.badRequest().body("Cannot refresh token");
        }
        log.info("Token refreshed! {}", newToken);
        return ResponseEntity.ok(newToken);
    }
}