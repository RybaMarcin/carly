package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.PartDetailsRequest;
import org.carly.core.partsmanagement.service.PartsFindService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parts")
public class PartsController {

    private static final String FIND_PART_DETAILS = "Find part details";
    private static final String FIND_FACTORY_PARTS = "Find factory parts";

    private final PartsFindService partsFindService;

    public PartsController(PartsFindService partsFindService) {
        this.partsFindService = partsFindService;
    }


    @GetMapping("/find-factory-parts/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTATOR', 'CARLY_COMPANY')")
    @ApiOperation(value = FIND_FACTORY_PARTS)
    public ResponseEntity<?> findFactoryParts(@PathVariable(name = "factoryId") String factoryId) {
        return partsFindService.findFactoryParts(factoryId);
    }

    @GetMapping("/find-part-details")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    @ApiOperation(value = FIND_PART_DETAILS)
    public ResponseEntity<?> findPartDetails(@RequestBody PartDetailsRequest request) {
        return partsFindService.findPartDetails(request);
    }

}
