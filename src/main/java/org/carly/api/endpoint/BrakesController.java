package org.carly.api.endpoint;


import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.partsmanagement.service.BrakeFindService;
import org.carly.core.partsmanagement.service.BrakeSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/brakes")
public class BrakesController {

    private static final String DELETE_BRAKES = "Delete brakes by id";
    private final BrakeFindService brakeFindService;
    private final BrakeSaveService brakeSaveService;

    public BrakesController(BrakeFindService brakeFindService,
                            BrakeSaveService brakeSaveService) {
        this.brakeFindService = brakeFindService;
        this.brakeSaveService = brakeSaveService;
    }

    @GetMapping("/brakes")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<BrakeResponse> findBrakes(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return brakeFindService.findBreaks(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public BrakeResponse findBrakeById(@PathVariable("id") String id) {
        return brakeFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<BrakeResponse> findAllBrakes() {
        return brakeFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public ResponseEntity<BrakeResponse> createBrake(@RequestBody BrakeRequest brakeRequest) {
        return brakeSaveService.createPart(brakeRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public ResponseEntity<BrakeResponse> updateBreak(@RequestBody BrakeRequest breaks) {
        return brakeSaveService.updatePart(breaks);
    }

    @DeleteMapping("/delete/{brakeId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_BRAKES)
    public ResponseEntity<SuccessResponse> deleteBrake(@PathVariable(name = "brakeId") String brakeId) {
        return brakeSaveService.deleteBrake(brakeId);
    }
}
