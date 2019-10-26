package org.carly.company_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.api.model.CompanySearchCriteriaRest;
import org.carly.company_management.core.mapper.CompanyMapper;
import org.carly.company_management.core.model.Company;
import org.carly.company_management.core.repository.CompanyMongoRepository;
import org.carly.company_management.core.repository.CompanyRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class CompanyFindService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyMongoRepository companyMongoRepository;

    public CompanyFindService(CompanyRepository companyRepository,
                              CompanyMapper companyMapper,
                              CompanyMongoRepository companyMongoRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyMongoRepository = companyMongoRepository;
    }

    public CompanyRest findCompanyById(ObjectId id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return companyMapper.simplifyRestObject(company);
    }

    //todo
    public CompanyRest findPendingCompany(ObjectId id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (company != null && company.getRequestStatus() == ChangeRequestStatus.PENDING) {
            log.info("Company with id {} was found! {}", id, company);
            return companyMapper.simplifyRestObject(company);
        }
        log.error("Company with id: {}, not found!", id);
        throw new EntityNotFoundException(NOT_FOUND);
    }

    public Page<CompanyRest> findCompanies(CompanySearchCriteriaRest searchCriteria, Pageable pageable) {
        return companyMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(companyMapper::simplifyRestObject);
    }

}