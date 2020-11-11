package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.api.rest.request.CompanySearchCriteriaRequest;
import org.carly.core.companymanagement.mapper.CompanyMapper;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.companymanagement.repository.CompanyMongoRepository;
import org.carly.core.companymanagement.repository.CompanyRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.usermanagement.model.User;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

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

    public CompanyResponse findCompanyById(ObjectId id) {
        User company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Company with id: {} was found!", id);
        return companyMapper.simplifyRestObject(company);
    }


    public Page<CompanyResponse> findCompanies(CompanySearchCriteriaRequest searchCriteria, Pageable pageable) {
        return companyMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(companyMapper::simplifyRestObject);
    }

}