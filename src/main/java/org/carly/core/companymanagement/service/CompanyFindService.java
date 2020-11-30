package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.CompanySearchCriteriaRequest;
import org.carly.api.rest.response.CompanyErrorResponse;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.core.companymanagement.mapper.CompanyMapper;
import org.carly.core.companymanagement.repository.CompanyMongoRepository;
import org.carly.core.companymanagement.repository.CompanyRepository;
import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.exception.CompanyException;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.exception.ErrorCode;
import org.carly.core.usermanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> findCompanyById(ObjectId id) {
        try {
            User company = getCompanyById(id);
            CompanyResponse companyResponse = companyMapper.simplifyRestObject(company);
            if (company.getRoles().contains(CarlyGrantedAuthority.of("CARLY_COMPANY"))) {
                companyResponse.setRole("CARLY_COMPANY");
            } else if (company.getRoles().contains(CarlyGrantedAuthority.of("CARLY_FACTORY"))) {
                companyResponse.setRole("CARLY_FACTORY");
            }
            return ResponseEntity.ok(companyResponse);
        } catch (CompanyException | EntityNotFoundException e) {
            log.error(getClass() + " - " + e.getMessage());
            return ResponseEntity.badRequest().body(new CompanyErrorResponse(e.getMessage()));
        }
    }

    public User getCompanyById(ObjectId id) {
        User company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        log.info("Company with id: {} was found!", id);
        if (company.getRoles().contains(CarlyGrantedAuthority.of("CARLY_CUSTOMER"))) {
            throw new CompanyException(company.getId() + " - entity is not Company");
        }
        return company;
    }


    public Page<CompanyResponse> findCompanies(CompanySearchCriteriaRequest searchCriteria, Pageable pageable) {
        return companyMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(companyMapper::simplifyRestObject);
    }
}
