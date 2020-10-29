package org.carly.core.usermanagement.service;

import org.carly.core.security.UserDetailsImpl;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.model.LoggedUserBuilder;
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
        return UserDetailsImpl.build(user);
    }

    private LoggedUser initLogin(User user) {
        LoggedUserBuilder loginUser = new LoggedUserBuilder()
                .withId(user.getId().toHexString())
                .withCompanyId(user.getCompanyId() == null ? null : user.getCompanyId().toHexString())
                .withEmail(user.getEmail())
                .withName(user.getFirstName())
                .withAuthorities(user.getRoles())
                .withEnabled(user.isEnabled());
        return loginUser.build();
    }
}
