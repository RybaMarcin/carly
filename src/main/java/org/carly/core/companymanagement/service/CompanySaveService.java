package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.core.companymanagement.mapper.CompanyMapper;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.companymanagement.model.CompanyChangeRequest;
import org.carly.core.companymanagement.repository.CompanyRepository;
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

    public ObjectId saveCompany(CompanyResponse companyResponse) {
        Company company = companyMapper.simplifyDomainObject(companyResponse);
        CompanyChangeRequest companyRequest = this.companyChangeRequest.saveCompanyChangeRequest(company);
        return companyRequest.getId();
    }
}