package org.carly.company_management.api.model.controller;

import org.bson.types.ObjectId;
import org.carly.company_management.api.model.CompanyRest;
import org.carly.company_management.core.service.CompanyFindService;
import org.carly.company_management.core.service.CompanySaveService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyFindService companyFindService;
    private final CompanySaveService companySaveService;

    public CompanyController(CompanyFindService companyFindService, CompanySaveService companySaveService) {
        this.companyFindService = companyFindService;
        this.companySaveService = companySaveService;
    }

    @GetMapping("{id}")
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
