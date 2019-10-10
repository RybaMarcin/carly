package org.carly.company_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.core.mapper.CompanyMapper;
import org.carly.company_management.core.model.Company;
import org.carly.company_management.core.model.CompanyChangeRequest;
import org.carly.company_management.core.repository.CompanyRepository;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CompanySaveService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyChangeRequestService companyChangeRequest;

    public CompanySaveService(CompanyRepository companyRepository, CompanyMapper companyMapper, CompanyChangeRequestService companyChangeRequest) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyChangeRequest = companyChangeRequest;
    }

    public ObjectId saveCompany(CompanyRest companyRest) {
        Company company = companyMapper.simplifyDomainObject(companyRest);
        CompanyChangeRequest companyRequest = this.companyChangeRequest.saveCompanyChangeRequest(company);
        return companyRequest.getId();
    }
}