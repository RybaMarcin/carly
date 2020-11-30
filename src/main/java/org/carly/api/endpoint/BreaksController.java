package org.carly.api.endpoint;


import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.partsmanagement.service.BreaksFindService;
import org.carly.core.partsmanagement.service.BrakeSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/breaks")
public class BreaksController {

    private static final String DELETE_BREAKS = "Delete breaks by id";
    private final BreaksFindService breaksFindService;
    private final BrakeSaveService brakeSaveService;

    public BreaksController(BreaksFindService breaksFindService,
                            BrakeSaveService brakeSaveService) {
        this.breaksFindService = breaksFindService;
        this.brakeSaveService = brakeSaveService;
    }

    @GetMapping("/breaks")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<BrakeResponse> findBreaks(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return breaksFindService.findBreaks(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public BrakeResponse findBreaksById(@PathVariable("id") String id) {
        return breaksFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all-breaks-by-company-id/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public Collection<BrakeResponse> findAllBreaksByCompanyId(@PathVariable(name = "companyId") String companyId) {
        return breaksFindService.findAllBreaksByCompanyId(companyId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_CUSTOMER')")
    public Collection<BrakeResponse> findAllBreaks() {
        return breaksFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public ResponseEntity<BrakeResponse> createBreaks(@RequestBody BrakeRequest brakeRequest) {
        return brakeSaveService.createPart(brakeRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public ResponseEntity<BrakeResponse> updateBreaks(@RequestBody BrakeRequest breaks) {
        return brakeSaveService.updatePart(breaks);
    }

    @DeleteMapping("/delete/{breakId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_BREAKS)
    public ResponseEntity<SuccessResponse> deleteBreaks(@PathVariable(name = "breakId") String breakId) {
        return brakeSaveService.deleteBreak(breakId);
    }
}
