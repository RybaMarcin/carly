package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.CompanyFactoryMatchRequest;
import org.carly.api.rest.request.CompanyMatchingRequest;
import org.carly.api.rest.response.CompanyMatchResponse;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company-match")
public class CompanyMatchController {

    private static final String CREATE_REQUEST_FOR_MATCHING_COMPANY_AND_FACTORY = "Create request for matching between company and factory";
    private static final String ADD_RESPONSE_FOR_COMPANY_MATCHING_BY_FACTORY = "Create response for company request by factory";
    private static final String ADD_DECLINE_FOR_REQUEST = "Decline request for matching";

    private final CompanyMatchingService matchingService;

    public CompanyMatchController(CompanyMatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping("/request")
    @ApiOperation(value = CREATE_REQUEST_FOR_MATCHING_COMPANY_AND_FACTORY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<CompanyMatchResponse> createRequestForCompanyMatching(@RequestBody CompanyMatchingRequest request) {
        return matchingService.createMatchingRequest(request);
    }

    @PostMapping("/response")
    @ApiOperation(value = ADD_RESPONSE_FOR_COMPANY_MATCHING_BY_FACTORY)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<CompanyMatchResponse> addResponseForCompanyMatchingRequest(@RequestBody @Valid CompanyFactoryMatchRequest matchResponse) {
        return matchingService.addResponseForRequest(matchResponse);
    }

    @GetMapping("/declined/{matchRequestId}")
    @ApiOperation(value = ADD_DECLINE_FOR_REQUEST)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public ResponseEntity<CompanyMatchResponse> declineRequestForCompanyMatching(@PathVariable(name = "matchRequestId") String matchingRequestId) {
        return matchingService.declineRequestMatching(matchingRequestId);
    }
}
