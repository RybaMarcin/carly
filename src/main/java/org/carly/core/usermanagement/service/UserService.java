package org.carly.core.usermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.core.shared.config.EntityAlreadyExistsException;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.security.config.LoggedUserProvider;
import org.carly.core.shared.security.exceptions.LoginOrPasswordException;
import org.carly.core.shared.security.model.*;
import org.carly.core.shared.utils.mail_service.MailService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.model.AddressRest;
import org.carly.core.usermanagement.model.CarlyUserRest;
import org.carly.core.usermanagement.model.LoginRest;
import org.carly.core.usermanagement.model.UserRest;
import org.carly.core.usermanagement.mapper.UserMapper;
import org.carly.core.usermanagement.model.Address;
import org.carly.core.usermanagement.model.OnRegistrationCompleteEvent;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.model.VerificationToken;
import org.carly.core.usermanagement.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import static org.carly.core.shared.security.model.UserRole.CHANGE_PASSWORD_PRIVILEGE;
import static java.lang.String.format;
import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final MessageSource messageSource;
    private final UserMapper userMapper;
    private final TimeService timeService;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final LoggedUserProvider loggedUserProvider;

    public UserService(UserRepository userRepository,
                       TokenService tokenService,
                       MessageSource messageSource,
                       UserMapper userMapper,
                       TimeService timeService,
                       ApplicationEventPublisher eventPublisher,
                       PasswordEncoder passwordEncoder,
                       MailService mailService,
                       LoggedUserProvider loggedUserProvider){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
        this.timeService = timeService;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.loggedUserProvider = loggedUserProvider;
    }

    @Transactional
    public User createUser(UserRest userRest, WebRequest request) {
        User registered = registrationNewUserAccount(userRest);
        publishRegistrationUser(registered, request);
        return registered;
    }

    private User registrationNewUserAccount(UserRest userRest) {
        if (emailExists(userRest.getEmail())) {
            throw new EntityAlreadyExistsException(format("Account with %s email already exists!", userRest.getEmail()));
        }
        User user = userMapper.simplifyDomainObject(userRest);
        user.setCode(UUID.randomUUID().toString().substring(0, 6));
        user.setPassword(passwordEncoder.encode(userRest.getPassword()));
        user.setCreatedAt(timeService.getLocalDate());
        user.setRole(List.of(CarlyGrantedAuthority.of(UserRole.CARLY_CUSTOMER.name())));
        user.setEnabled(false);
        return userRepository.save(user);
    }

    private void publishRegistrationUser(User user, WebRequest request) {
        OnRegistrationCompleteEvent onRegistrationCompleteEvent;
        try {
            String appUrl = request.getContextPath();
            onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(appUrl, request.getLocale(), user);
            eventPublisher.publishEvent(onRegistrationCompleteEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
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

    public CarlyUserRest login(LoginRest userRest) throws LoginOrPasswordException {
        User user = userRepository.findByEmail(userRest.getEmail()).orElseThrow(() -> new LoginOrPasswordException(NOT_FOUND));
        //todo: Line commented out just for testing.

        //        boolean matches = passwordEncoder.matches(userRest.getPassword(), user.getPassword());
        boolean matches = true;
        if (matches) {
            LoggedUser loggedUser = loadUserByUsername(userRest.getEmail());
            if (loggedUser.isEnabled()) {
                CarlyUserBuilder carlyUserBuilder = new CarlyUserBuilder();
                return carlyUserBuilder
                        .withId(loggedUser.getId())
                        .withCompanyId(loggedUser.getCompanyId())
                        .withEmail(loggedUser.getEmail())
                        .withName(loggedUser.getName())
                        .withRole(provideCurrentRole(loggedUser.getRoles()))
                        .build();
            }
        }
        throw new LoginOrPasswordException("Password or Email was incorrect");
    }

    @Override
    public LoggedUser loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return initLogin(user);
    }

    private LoggedUser initLogin(User user) {
        LoggedUserBuilder loginUser = new LoggedUserBuilder()
                .withId(user.getId().toHexString())
                .withCompanyId(user.getCompanyId() == null ? null : user.getCompanyId().toHexString())
                .withEmail(user.getEmail())
                .withName(user.getFirstName())
                .withAuthorities(user.getRole())
                .withEnabled(user.isEnabled());
        return loginUser.build();
    }

    private static UserRole provideCurrentRole(List<UserRole> roles) {
        return roles.iterator().next();
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
        if (user.getRole().contains(CarlyGrantedAuthority.of(CHANGE_PASSWORD_PRIVILEGE.name()))) {
            user.setPassword(passwordEncoder.encode(password));
            user.getRole().removeIf(role -> role.getUserRole() == CHANGE_PASSWORD_PRIVILEGE);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity addAddress(ObjectId userId, AddressRest newAddress) {
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