package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.utils.mail_service.MailService;
import org.carly.shared.utils.time.service.TimeService;
import org.carly.user_management.api.model.CarlyUserRest;
import org.carly.user_management.api.model.LoginRest;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.config.LoggedUserProvider;
import org.carly.user_management.core.mapper.UserMapper;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.UserRepository;
import org.carly.user_management.security.*;
import org.carly.user_management.security.config.TokenTimeException;
import org.carly.user_management.security.token.VerificationToken;
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
import java.util.List;
import java.util.Locale;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

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
                       LoggedUserProvider loggedUserProvider) {
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
            throw new EntityAlreadyExistsException("Account with that email already exists!" + userRest.getEmail());
        }
        User user = userMapper.simplifyDomainObject(userRest);
        user.setPassword(passwordEncoder.encode(userRest.getPassword()));
        user.setCreatedAt(timeService.getLocalDate());
        user.setRole(List.of(CarlyGrantedAuthority.of(UserRole.CUSTOMER.name())));
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
            return messageSource.getMessage("auth.message.expired", null, locale);
        }
        User user = verificationToken.getUser();
        LocalDateTime clickedDateTime = timeService.getLocalDateTime();

        if (clickedDateTime.isAfter(verificationToken.getExpiryDate()) || clickedDateTime.isEqual(verificationToken.getExpiryDate())) {
            return messageSource.getMessage("auth.message.expired", null, locale);
        }
        user.setEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok().toString();
    }

    public CarlyUserRest login(LoginRest userRest) {
        User user = userRepository.findByEmail(userRest.getEmail()).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        boolean matches = passwordEncoder.matches(userRest.getPassword(), user.getPassword());
        if (matches) {
            LoggedUser loggedUser = loadUserByUsername(userRest.getEmail());
            if (loggedUser.isEnabled()) {
                CarlyUserBuilder carlyUserBuilder = new CarlyUserBuilder();
                return carlyUserBuilder
                        .withId(loggedUser.getId())
                        .withEmail(loggedUser.getEmail())
                        .withName(loggedUser.getName())
                        .withRole(provideCurrentRole(loggedUser.getRoles()))
                        .build();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public LoggedUser loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return initLogin(user);
    }

    private LoggedUser initLogin(User user) {
        LoggedUserBuilder loginUser = new LoggedUserBuilder()
                .withId(user.getId().toHexString())
                .withEmail(user.getEmail())
                .withName(user.getName())
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
        String message = messageSource.getMessage("message.resetPassowrd", null, request.getLocale());
        mailService.send(mailService.constructSimpleMessage("Reset password", message + "\r\n" + url, user));
        return ResponseEntity.ok("Mail was sent");
    }

    public ResponseEntity changePassword(String id, String token) {
        User user = userRepository.findById(new ObjectId(id)).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new IllegalArgumentException();
        }
        User userFromVerification = verificationToken.getUser();
        if (userFromVerification.getId().equals(user.getId())) {
            LocalDateTime clickedDateTime = timeService.getLocalDateTime();
            if (clickedDateTime.isAfter(verificationToken.getExpiryDate()) || clickedDateTime.isEqual(verificationToken.getExpiryDate())) {
                throw new TokenTimeException("Token is expire!");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken
                    (user, null, List.of(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity saveNewPassword(LoggedUser loggedUser, String password) {
        User user = userRepository.findById(new ObjectId(loggedUser.getId())).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        user.setPassword(passwordEncoder.encode(password));
        user.setMatchingPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}