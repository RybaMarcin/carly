package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.CompanyMatchingRequest;
import org.carly.api.rest.response.MatchResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company-match")
public class CompanyFactoryMatchController {

    private static final String CANCEL_MATCHING_WITH_COMPANY = "Cancel matching with company";
    private static final String CANCEL_MATCHING_WITH_FACTORY = "Cancel matching with factory";
    private static final String CONFIRM_MATCHING_WITH_COMPANY = "Confirm matching between company and factory";
    private static final String CREATE_REQUEST_MATCHING_WITH_FACTORY = "Create request for matching between company and factory";
    private static final String FIND_ALL_POTENTIAL_FACTORIES = "Find all non matched factories";
    private static final String FIND_ALL_ACCEPTED_AND_PENDING_MATCHING = "Find all accepted and pending matching";
    private static final String FIND_ALL_ACCEPTED_MATCHING_WITH_FACTORY = "Find all accepted matching with factory";
    private static final String FIND_ALL_PENDING_REQUESTS = "Find all pending requests for factory";

    private final CompanyMatchingService companyMatchingService;

    public CompanyFactoryMatchController(CompanyMatchingService companyMatchingService) {
        this.companyMatchingService = companyMatchingService;
    }

    /* Company -> Factory START */
    @GetMapping("/all-accepted-pending-matching/{companyId}")
    @ApiOperation(value = FIND_ALL_ACCEPTED_AND_PENDING_MATCHING)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<?> findAllMatchedAndPendingContractsByCompanyId(@PathVariable(name = "companyId") String companyId) {
        return companyMatchingService.findAllMatchedAndPendingContractsByCompanyId(companyId);
    }

    @GetMapping("/potential-factories/{companyId}")
    @ApiOperation(value = FIND_ALL_POTENTIAL_FACTORIES)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<?> findAllFactoriesToMatchByCompanyId(@PathVariable(name = "companyId") String companyId) {
        return companyMatchingService.findAllFactoriesToMatchByCompanyId(companyId);
    }

    @PostMapping("/request-matching-with-factory")
    @ApiOperation(value = CREATE_REQUEST_MATCHING_WITH_FACTORY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<SuccessResponse> createRequestMatchingWithFactory(@RequestBody @Valid CompanyMatchingRequest request) {
        return companyMatchingService.createMatchingRequest(request);
    }

    @PostMapping("/cancel-matching-with-factory")
    @ApiOperation(value = CANCEL_MATCHING_WITH_FACTORY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<?> cancelMatchingWithFactory(@RequestBody MatchResponse matchResponse) {
        return companyMatchingService.cancelMatching(matchResponse);
    }
    /* Company -> Factory END */

    /* Factory -> Company START */
    @GetMapping("/all-accepted-matching/{factoryId}")
    @ApiOperation(value = FIND_ALL_ACCEPTED_MATCHING_WITH_FACTORY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<?> findAllAcceptedMatching(@PathVariable(name = "factoryId") String factoryId) {
        return companyMatchingService.findAllAcceptedMatching(factoryId);
    }

    @GetMapping("/all-pending-requests/{factoryId}")
    @ApiOperation(value = FIND_ALL_PENDING_REQUESTS)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<?> findAllPendingContracts(@PathVariable(name = "factoryId") String factoryId) {
        return companyMatchingService.findAllPendingContracts(factoryId);
    }

    @PostMapping("/confirm-matching-with-company")
    @ApiOperation(value = CONFIRM_MATCHING_WITH_COMPANY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<?> confirmMatchingWithCompany(@RequestBody MatchResponse matchResponse) {
        return companyMatchingService.confirmMatchingWithCompany(matchResponse);
    }

    @PostMapping("/cancel-matching-with-company")
    @ApiOperation(value = CANCEL_MATCHING_WITH_COMPANY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<?> cancelMatchingWithCompany(@RequestBody MatchResponse matchResponse) {
        return companyMatchingService.cancelMatching(matchResponse);
    }
    /* Factory -> Company END */
}
