package org.carly.company_management.core.mapper;

import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.core.model.Company;
import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.core.mapper.CarMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyMapper implements MapperService<CompanyRest, Company> {


    private CarMapper carMapper;

    @Override
    public CompanyRest mapFromDomainObject(Company domain, CompanyRest rest) {
        if (domain.getId() != null) {
            rest.setId(rest.getId());
        }
        rest.setAddress(domain.getAddress());
        rest.setCars(domain.getCars().stream().map(e -> carMapper.simplifyRestObject(e)).collect(Collectors.toList()));
        rest.setLogoId(domain.getLogoId());
        rest.setName(domain.getName());
        rest.setYearOfEstablishment(domain.getYearOfEstablishment());
        return rest;
    }

    @Override
    public Company mapToDomainObject(Company domain, CompanyRest rest) {
        if (rest.getId() != null) {
            domain.setId(rest.getId());
        }
        domain.setAddress(domain.getAddress());
        domain.setCars(rest.getCars().stream().map(e -> carMapper.simplifyDomainObject(e)).collect(Collectors.toList()));
        domain.setName(rest.getName());
        domain.setYearOfEstablishment(rest.getYearOfEstablishment());
        return domain;
    }

    @Override
    public CompanyRest simplifyRestObject(Company domain) {
        CompanyRest rest = new CompanyRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Company simplifyDomainObject(CompanyRest rest) {
        Company domain = new Company();
        return mapToDomainObject(domain, rest);
    }
}
