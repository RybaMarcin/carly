package org.carly.user_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.utils.time.service.TimeService;
import org.carly.user_management.api.model.CarlyUserRest;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.mapper.UserMapper;
import org.carly.user_management.core.model.OnRegistrationCompleteEvent;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.UserRepository;
import org.carly.user_management.security.*;
import org.carly.user_management.security.token.VerificationToken;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final MessageSource messageSource;
    private final UserMapper userMapper;
    private final TimeService timeService;
    private final ApplicationEventPublisher eventPublisher;

    public UserService(UserRepository userRepository,
                       TokenService tokenService, MessageSource messageSource, UserMapper userMapper,
                       TimeService timeService,
                       ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
        this.timeService = timeService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public User createUser(UserRest userRest, WebRequest request) {
        User registered = registrationNewUserAccount(userRest);
        publishRegistrationUser(registered, request);
        return registered;
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

    private User registrationNewUserAccount(UserRest userRest) {
        if (emailExists(userRest.getEmail())) {
            throw new EntityAlreadyExistsException("Account with that email already exists!" + userRest.getEmail());
        }
        User user = userMapper.simplifyDomainObject(userRest);
        user.setCreatedAt(timeService.getLocalDate());
        user.setEnabled(false);
        return userRepository.save(user);
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
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return messageSource.getMessage("auth.message.expired", null, locale);
        }
        user.setEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok().toString();
    }

    public CarlyUserRest login(UserRest userRest) {
        User user = userRepository.findByEmail(userRest.getEmail()).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        LoggedUser loggedUser = initLogin(user);
        if (loggedUser.isEnabled()) {
            CarlyUserBuilder carlyUserBuilder = new CarlyUserBuilder();
            return carlyUserBuilder
                    .withId(user.getId().toHexString())
                    .withEmail(user.getEmail())
                    .withName(user.getName())
                    .withRole(provideCurrentRole(user.getRole()))
                    .build();
        }
        return null;
    }

    private LoggedUser initLogin(User user) {
        LoggedUserBuilder loginUser = new LoggedUserBuilder()
                .withId(user.getId().toHexString())
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withAuthorities(user.getRole());
        return loginUser.build();
    }

    private UserRole provideCurrentRole(List<CarlyGrantedAuthority> roles) {
        return roles.iterator().next().getUserRole();
    }

}
