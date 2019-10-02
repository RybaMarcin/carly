package org.carly.user_management.core.service;

import org.bson.types.ObjectId;
import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.user_management.api.UserRest;
import org.carly.user_management.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ObjectId createUser(UserRest userRest) {
        boolean present = userRepository.findByEmail(userRest.getEmail()).isPresent();
        if (present) {
            throw new EntityAlreadyExistsException("Already exists!");
        }
    }
}
