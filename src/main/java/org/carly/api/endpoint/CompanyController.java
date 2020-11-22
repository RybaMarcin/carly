package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.api.rest.criteria.CompanySearchCriteriaRequest;
import org.carly.core.companymanagement.service.CompanyFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api//company")
public class CompanyController {

    private static final String FIND_COMPANIES_BY_NAME = "Find companies by names";
    private static final String FIND_COMPANY_BY_ID = "Find company by id";

    private final CompanyFindService companyFindService;

    public CompanyController(CompanyFindService companyFindService) {
        this.companyFindService = companyFindService;
    }

    @GetMapping("/company")
    @ApiOperation(value = FIND_COMPANIES_BY_NAME)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<CompanyResponse> findCompanies(CompanySearchCriteriaRequest searchCriteria,
                                               Pageable pageable) {
        return companyFindService.findCompanies(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = FIND_COMPANY_BY_ID)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<?> findCompanyById(@PathVariable("id") String id) {
        return companyFindService.findCompanyById(new ObjectId(id));
    }
}
