package org.carly.company_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.core.mapper.CompanyMapper;
import org.carly.company_management.core.model.Company;
import org.carly.company_management.core.repository.CompanyRepository;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class CompanyFindService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyFindService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyRest findCompanyById(ObjectId id) {
        Company company = companyRepository.findById(id);
        return companyMapper.simplifyRestObject(company);
    }

    public CompanyRest findPendingCompany(Long id) {
        Company company = companyRepository.findById(id);
        if (company != null && company.getRequestStatus() == ChangeRequestStatus.PENDING) {
            log.info("Company with id {} was found! {}", id, company);
            return companyMapper.simplifyRestObject(company);
        }
        log.error("Company with id: {}, not found!", id);
        throw new EntityNotFoundException();
    }
}