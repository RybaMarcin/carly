package org.carly.company_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.company_management.core.model.Company;
import org.carly.company_management.core.model.CompanyChangeRequest;
import org.carly.company_management.core.repository.CompanyChangeRequestRepository;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

@Slf4j
@Service
public class CompanyChangeRequestService {

    private final CompanyChangeRequestRepository companyChangeRequestRepository;

    public CompanyChangeRequestService(CompanyChangeRequestRepository companyChangeRequestRepository) {
        this.companyChangeRequestRepository = companyChangeRequestRepository;
    }

    public CompanyChangeRequest saveCompanyChangeRequest(Company company) {
        CompanyChangeRequest companyChangeRequest = companyChangeRequestRepository.find("company = ?1 and status = ?2", company, ChangeRequestStatus.PENDING).singleResult();
        if (companyChangeRequest == null) {
            CompanyChangeRequest request = createCompanyChangeRequest(company, ChangeRequestStatus.PENDING);
            companyChangeRequestRepository.persistAndFlush(request);
            log.info("Company {} -saved to pending mode, waiting for accept/decline", company);
            return request;
        }
        log.error("Company: {} already exists!", company);
        throw new EntityExistsException();
    }

    private CompanyChangeRequest createCompanyChangeRequest(Company company, ChangeRequestStatus status) {
        CompanyChangeRequest result = new CompanyChangeRequest();
        result.setCompany(company);
        result.setCreateAt(LocalDate.now());
        result.setStatus(status);
        return result;
    }
}
