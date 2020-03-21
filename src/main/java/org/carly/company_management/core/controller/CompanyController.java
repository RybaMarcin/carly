package org.carly.company_management.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.api.model.CompanySearchCriteriaRest;
import org.carly.company_management.core.service.CompanyFindService;
import org.carly.company_management.core.service.CompanySaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private final CompanyFindService companyFindService;
    private final CompanySaveService companySaveService;

    public CompanyController(CompanyFindService companyFindService, CompanySaveService companySaveService) {
        this.companyFindService = companyFindService;
        this.companySaveService = companySaveService;
    }

    @GetMapping("/company")
    public Page<CompanyRest> findCompanies(CompanySearchCriteriaRest searchCriteria,
                                           Pageable pageable) {
        return companyFindService.findCompanies(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public CompanyRest findCompanyById(@PathVariable("id") ObjectId id) {
        return companyFindService.findCompanyById(id);
    }

    @GetMapping("/pending/{id}")
    public CompanyRest findPendingCompanyById(@PathVariable("id") ObjectId id) {
        return companyFindService.findPendingCompany(id);
    }

    @PostMapping("/create")
    public ObjectId createCompany(@RequestBody CompanyRest companyRest) {
        return companySaveService.saveCompany(companyRest);
    }
}
