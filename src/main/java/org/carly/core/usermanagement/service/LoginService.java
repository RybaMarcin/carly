package org.carly.core.usermanagement.service;

import org.carly.core.security.model.LoggedUser;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.usermanagement.model.User;
import org.carly.core.usermanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return LoggedUser.build(user);
    }
}
