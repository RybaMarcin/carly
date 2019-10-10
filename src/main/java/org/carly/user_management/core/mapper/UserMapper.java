package org.carly.user_management.core.mapper;

import org.carly.shared.utils.MapperService;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper implements MapperService<UserRest, User> {

    @Override
    public UserRest mapFromDomainObject(User domain, UserRest rest) {
        rest.setName(domain.getName());
        rest.setLastName(domain.getLastName());
        rest.setEmail(domain.getEmail());
        return rest;
    }

    @Override
    public User mapToDomainObject(User domain, UserRest rest) {
        domain.setName(rest.getName());
        domain.setLastName(rest.getLastName());
        domain.setEmail(rest.getEmail());
        domain.setRole(new ArrayList<>());
        return domain;
    }

    @Override
    public UserRest simplifyRestObject(User domain) {
        UserRest rest = new UserRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public User simplifyDomainObject(UserRest rest) {
        User domain = new User();
        return mapToDomainObject(domain, rest);
    }
}
