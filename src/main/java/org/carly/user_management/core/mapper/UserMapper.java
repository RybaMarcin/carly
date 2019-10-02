package org.carly.user_management.core.mapper;

import org.carly.shared.utils.MapperService;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperService<UserRest, User> {

    @Override
    public UserRest mapFromDomainObject(User domain, UserRest rest) {
        rest.setEmail(domain.getEmail());
        rest.setPassword(domain.getPassword());
        return rest;
    }

    @Override
    public User mapToDomainObject(User domain, UserRest rest) {
        domain.setEmail(rest.getEmail());
        domain.setPassword(rest.getPassword());
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
