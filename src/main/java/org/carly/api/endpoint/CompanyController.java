package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.CompanyRest;
import org.carly.api.rest.CompanySearchCriteriaRest;
import org.carly.core.companymanagement.service.CompanyFindService;
import org.carly.core.companymanagement.service.CompanySaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/company")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<CompanyRest> findCompanies(CompanySearchCriteriaRest searchCriteria,
                                           Pageable pageable) {
        return companyFindService.findCompanies(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public CompanyRest findCompanyById(@PathVariable("id") String id) {
        return companyFindService.findCompanyById(new ObjectId(id));
    }

    @GetMapping("/pending/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public CompanyRest findPendingCompanyById(@PathVariable("id") String id) {
        return companyFindService.findPendingCompany(new ObjectId(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ObjectId createCompany(@RequestBody CompanyRest companyRest) {
        return companySaveService.saveCompany(companyRest);
    }
}
