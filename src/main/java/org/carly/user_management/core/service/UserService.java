package org.carly.user_management.core.service;

import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.utils.TimeService;
import org.carly.user_management.api.model.CarlyUserRest;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.mapper.UserMapper;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.UserRepository;
import org.carly.user_management.security.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TimeService timeService;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       TimeService timeService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.timeService = timeService;
    }

    @Transactional
    public User createUser(UserRest userRest) {
        User registered;
        try {
            registered = registrationNewUserAccount(userRest);
        } catch (EntityAlreadyExistsException e) {
            return null;
        }
        return registered;
    }

    private User registrationNewUserAccount(UserRest userRest) throws EntityAlreadyExistsException {
        if (emailExists(userRest.getEmail())) {
            throw new EntityAlreadyExistsException("Account with that email already exists!" + userRest.getEmail());
        }
        User user = userMapper.simplifyDomainObject(userRest);
        user.setRole(new ArrayList<>());
        user.getRole().add(new CarlyGrantedAuthority(UserRole.CUSTOMER));
        user.setCreatedAt(timeService.getLocalDate());
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
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
