package org.carly.core.usermanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.core.customermanagement.model.Customer;
import org.carly.core.shared.utils.MapperService;
import org.carly.api.rest.request.AddressRequest;
import org.carly.core.usermanagement.model.UserRest;
import org.carly.core.shared.model.Address;
import org.carly.core.usermanagement.model.Gender;
import org.carly.core.usermanagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperService<UserRest, User> {

    @Override
    public UserRest mapFromDomainObject(User domain, UserRest rest) {
        rest.setFirstName(domain.getCustomer().getFirstName());
        rest.setLastName(domain.getCustomer().getLastName());
        rest.setPhoneNumber(domain.getPhoneNumber());
        rest.setGender(domain.getCustomer().getGender().name());
        rest.setEmail(domain.getEmail());
        return rest;
    }

    @Override
    public User mapToDomainObject(User domain, UserRest rest) {
        domain.setId(rest.getId() != null ? rest.getId() : new ObjectId());
        Customer customer = new Customer();
        domain.setCustomer(customer);
        domain.getCustomer().setFirstName(rest.getFirstName());
        domain.getCustomer().setLastName(rest.getLastName());
        domain.setPhoneNumber(rest.getPhoneNumber());
        domain.setEmail(rest.getEmail());
        domain.getCustomer().setGender(Gender.valueOf(rest.getGender()));
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

    public Address mapAddressToDomain(AddressRequest rest) {
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
