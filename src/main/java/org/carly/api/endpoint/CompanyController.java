package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.api.rest.request.CompanySearchCriteriaRequest;
import org.carly.core.companymanagement.service.CompanyFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyFindService companyFindService;

    public CompanyController(CompanyFindService companyFindService) {
        this.companyFindService = companyFindService;
    }

    @GetMapping("/company")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<CompanyResponse> findCompanies(CompanySearchCriteriaRequest searchCriteria,
                                               Pageable pageable) {
        return companyFindService.findCompanies(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public CompanyResponse findCompanyById(@PathVariable("id") String id) {
        return companyFindService.findCompanyById(new ObjectId(id));
    }
}