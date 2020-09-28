package org.carly.company_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.company_management.core.model.Company;
import org.carly.company_management.core.model.CompanyChangeRequest;
import org.carly.company_management.core.repository.CompanyChangeRequestRepository;
import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class CompanyChangeRequestService {

    private final CompanyChangeRequestRepository companyChangeRequestRepository;

    public CompanyChangeRequestService(CompanyChangeRequestRepository companyChangeRequestRepository) {
        this.companyChangeRequestRepository = companyChangeRequestRepository;
    }

     CompanyChangeRequest saveCompanyChangeRequest(Company company) {
        CompanyChangeRequest companyChangeRequest = companyChangeRequestRepository
                .findById(company.getId()).orElse(null);
        if (companyChangeRequest == null) {
            CompanyChangeRequest request = createCompanyChangeRequest(company);
            companyChangeRequestRepository.save(request);
            log.info("Company {} -saved to pending mode, waiting for accept/decline", company);
            return request;
        }
        log.error("Company: {} already exists in change request!", company);
        throw new EntityAlreadyExistsException("Already exists");
    }

    private CompanyChangeRequest createCompanyChangeRequest(Company company) {
        CompanyChangeRequest result = new CompanyChangeRequest();
        result.setCompany(company);
        result.setCreateAt(LocalDate.now());
        result.setStatus(ChangeRequestStatus.PENDING);
        return result;
    }
}
