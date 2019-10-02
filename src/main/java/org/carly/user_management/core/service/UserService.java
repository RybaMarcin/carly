package org.carly.user_management.core.service;

import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.user_management.security.LoggedUser;
import org.carly.user_management.security.UserRole;
import org.carly.shared.utils.TimeService;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.mapper.UserMapper;
import org.carly.user_management.core.model.User;
import org.carly.user_management.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user.setRole(UserRole.CUSTOMER);
        user.setCreatedAt(timeService.getLocalDate());
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public LoggedUser login(UserRest userRest) {
        return null;
    }
}
