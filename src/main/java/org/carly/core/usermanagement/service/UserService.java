package org.carly.core.usermanagement.service;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.auth.JwtResponse;
import org.carly.api.rest.auth.MessageResponse;
import org.carly.api.rest.auth.request.LoginRequest;
import org.carly.api.rest.auth.request.SignupRequest;
import org.carly.core.security.JwtUtils;
import org.carly.core.security.LoggedUserProvider;
import org.carly.core.security.UserDetailsImpl;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.mapper.UserMapper;
import org.carly.core.usermanagement.model.*;
import org.carly.core.usermanagement.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.carly.core.shared.security.model.UserRole.CHANGE_PASSWORD_PRIVILEGE;
import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service("userDetailsService")
public class UserService {

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
                       LoggedUserProvider loggedUserProvider, AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtUtils jwtUtils) {
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


    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (Boolean.FALSE.equals(userDetails.getEnabled())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Account is not active"));
        }
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId().toHexString(),
                userDetails.getEmail(),
                userDetails.getCompanyId() != null ? userDetails.getCompanyId().toHexString() : "",
                userDetails.getFirstName(),
                userDetails.getLastName(),
                roles));
    }

    public ResponseEntity<?> register(SignupRequest signUpRequest, WebRequest webRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                Gender.valueOf(signUpRequest.getGender()),
                encoder.encode(signUpRequest.getPassword()));

        user.setRoles(List.of(CarlyGrantedAuthority.of("CARLY_CUSTOMER")));
        userRepository.save(user);
        publishRegistrationUser(user, webRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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

    public ResponseEntity saveNewPassword(LoggedUser loggedUser, String password) {
        User user = userRepository.findById(new ObjectId(loggedUser.getId())).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (user.getRoles().contains(CarlyGrantedAuthority.of(CHANGE_PASSWORD_PRIVILEGE.name()))) {
            user.setPassword(passwordEncoder.encode(password));
            user.getRoles().removeIf(role -> role.getUserRole() == CHANGE_PASSWORD_PRIVILEGE);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity<AddressRest> addAddress(ObjectId userId, AddressRest newAddress) {
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
}