package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.response.CompanyFactoriesMatched;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/factory")
public class FactoryController {


    private static final String FIND_ALL_MATCHED_FACTORIES = "Find all matched factories";

    private final CompanyMatchingService companyMatchingService;

    public FactoryController(CompanyMatchingService companyMatchingService) {
        this.companyMatchingService = companyMatchingService;
    }

    @GetMapping("/all-matched-factories/{companyId}")
    @ApiOperation(value = FIND_ALL_MATCHED_FACTORIES)
    public ResponseEntity<?> getAllMatchedFactoriesForCompany(@PathVariable(value = "companyId") String companyId) {
        return companyMatchingService.getAllMatchedFactoriesByCompanyId(companyId);
    }
}
