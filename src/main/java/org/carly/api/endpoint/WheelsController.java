package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.WheelsSearchCriteriaRequest;
import org.carly.api.rest.request.WheelsRequest;
import org.carly.api.rest.response.WheelsResponse;
import org.carly.core.partsmanagement.service.WheelsFindService;
import org.carly.core.partsmanagement.service.WheelsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/wheels")
public class WheelsController {

    private final WheelsFindService wheelsFindService;
    private final WheelsSaveService wheelsSaveService;

    public WheelsController(WheelsFindService wheelsFindService,
                            WheelsSaveService wheelsSaveService) {
        this.wheelsFindService = wheelsFindService;
        this.wheelsSaveService = wheelsSaveService;
    }

    @GetMapping("/wheels")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<WheelsResponse> findWheels(WheelsSearchCriteriaRequest searchCriteria,
                                           Pageable pageable) {
        return wheelsFindService.findWheels(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<WheelsResponse> findWheelsById(@PathVariable("id") String id) {
        return wheelsFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<WheelsResponse> findAllWheels() {
        return wheelsFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public ResponseEntity<WheelsResponse> createWheels(@RequestBody WheelsRequest wheels) {
        return wheelsSaveService.createPart(wheels);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public ResponseEntity<WheelsResponse> updateWheels(@RequestBody WheelsRequest wheels) {
        return wheelsSaveService.updatePart(wheels);
    }
}