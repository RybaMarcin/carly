package org.carly.core.companymanagement.mapper;

import org.carly.api.rest.response.AddressResponse;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.shared.model.Address;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements MapperService<CompanyResponse, Company> {

    @Override
    public CompanyResponse mapFromDomainObject(Company domain, CompanyResponse rest) {

        rest.setCompanyName(domain.getName());
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
    public Company mapToDomainObject(Company domain, CompanyResponse rest) {
        domain.setName(rest.getCompanyName());
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
    public CompanyResponse simplifyRestObject(Company domain) {
        CompanyResponse rest = new CompanyResponse();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Company simplifyDomainObject(CompanyResponse rest) {
        Company domain = new Company();
        return mapToDomainObject(domain, rest);
    }
}
