package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.carly.core.companymanagement.service.FactoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factory")
public class FactoryController {

    public static final String FIND_ALL_MATCHED_FACTORIES = "Find all matched factories";
    public static final String GET_FACTORY_BY_ID = "Get factory by id";

    private final CompanyMatchingService companyMatchingService;
    private final FactoryService factoryService;

    public FactoryController(CompanyMatchingService companyMatchingService,
                             FactoryService factoryService) {
        this.companyMatchingService = companyMatchingService;
        this.factoryService = factoryService;
    }

    @GetMapping("/all-matched-factories/{companyId}")
    @ApiOperation(value = FIND_ALL_MATCHED_FACTORIES)
    public ResponseEntity<?> getAllMatchedFactoriesForCompany(@PathVariable(value = "companyId") String companyId) {
        return companyMatchingService.getAllMatchedFactoriesByCompanyId(companyId);
    }

    @PostMapping("/get-factories-details")
    @ApiOperation(value = GET_FACTORY_BY_ID)
    public ResponseEntity<?> getFactoryById(@RequestBody FactoryRequest factoryRequest) {
        return factoryService.getFactoryById(factoryRequest);
    }
}
