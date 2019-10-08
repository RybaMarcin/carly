package org.carly.user_management.core.config;

import org.carly.user_management.security.LoggedUser;
import org.carly.user_management.security.config.LoggedUserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class LoggedUserProvider {

    public LoggedUser provideCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !(authentication.getPrincipal() instanceof LoggedUser)) {
            throw new LoggedUserNotFoundException();
        }
        return (LoggedUser) authentication.getPrincipal();
    }
}
