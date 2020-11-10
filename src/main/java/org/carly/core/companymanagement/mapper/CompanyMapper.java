package org.carly.core.companymanagement.mapper;

import org.carly.api.rest.response.AddressResponse;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.shared.model.Address;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.usermanagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements MapperService<CompanyResponse, User> {

    @Override
    public CompanyResponse mapFromDomainObject(User domain, CompanyResponse rest) {

        rest.setCompanyName(domain.getCompany().getName());
        rest.setEmail(domain.getEmail());
        rest.setPhone(domain.getPhoneNumber());
        AddressResponse addressResponse = new AddressResponse(
                domain.getAddress().getStreet(), domain.getAddress().getNumber(), domain.getAddress().getFlat(),
                domain.getAddress().getTown(), domain.getAddress().getTown(), domain.getAddress().getZipCode()
        );

        rest.setAddress(addressResponse);
        return rest;
    }

    @Override
    public User mapToDomainObject(User domain, CompanyResponse rest) {
        domain.getCompany().setName(rest.getCompanyName());
        domain.setEmail(rest.getEmail());
        domain.setPhoneNumber(rest.getPhone());
        Address address = new Address(
                rest.getAddress().getStreet(), rest.getAddress().getNumber(), rest.getAddress().getFlat(),
                rest.getAddress().getTown(), rest.getAddress().getTown(), rest.getAddress().getZipCode()
        );
        domain.setAddress(address);
        return domain;
    }

    @Override
    public CompanyResponse simplifyRestObject(User domain) {
        CompanyResponse rest = new CompanyResponse();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public User simplifyDomainObject(CompanyResponse rest) {
        Company domain = new Company();
        return mapToDomainObject(domain, rest);
    }
}
