package org.carly.user_management.core.mapper;

import org.bson.types.ObjectId;
import org.carly.shared.utils.MapperService;
import org.carly.user_management.api.model.AddressRest;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.Address;
import org.carly.user_management.core.model.Gender;
import org.carly.user_management.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperService<UserRest, User> {

    @Override
    public UserRest mapFromDomainObject(User domain, UserRest rest) {
        rest.setFirstName(domain.getFirstName());
        rest.setLastName(domain.getLastName());
        rest.setPhoneNumber(domain.getPhoneNumber());
        rest.setGender(domain.getGender().name());
        rest.setEmail(domain.getEmail());
        return rest;
    }

    @Override
    public User mapToDomainObject(User domain, UserRest rest) {
        domain.setId(rest.getId() != null ? rest.getId() : new ObjectId());
        domain.setFirstName(rest.getFirstName());
        domain.setLastName(rest.getLastName());
        domain.setPhoneNumber(rest.getPhoneNumber());
        domain.setEmail(rest.getEmail());
        domain.setGender(Gender.valueOf(rest.getGender()));
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

    public Address mapAddressToDomain(AddressRest rest) {
        Address domain = new Address();
        domain.setStreet(rest.getStreet());
        domain.setNumber(rest.getNumber());
        domain.setFlat(rest.getFlat());
        domain.setTown(rest.getTown());
        domain.setZipCode(rest.getZipCode());
        domain.setCountry(rest.getCountry());
        return domain;
    }
}
