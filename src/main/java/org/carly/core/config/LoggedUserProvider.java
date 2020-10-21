package org.carly.core.config;

import org.carly.core.shared.security.model.LoggedUser;
import org.carly.core.shared.security.exceptions.LoggedUserNotFoundException;
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
