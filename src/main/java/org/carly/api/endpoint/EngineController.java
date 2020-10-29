package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EngineRest;
import org.carly.api.rest.partsmanagement.criteria.EngineSearchCriteriaRest;
import org.carly.core.partsmanagement.service.EngineFindService;
import org.carly.core.partsmanagement.service.EngineSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/engines")
public class EngineController {

    private final EngineFindService engineFindService;
    private final EngineSaveService engineSaveService;

    public EngineController(EngineFindService engineFindService,
                            EngineSaveService engineSaveService) {
        this.engineFindService = engineFindService;
        this.engineSaveService = engineSaveService;
    }

    @GetMapping("/engines")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria,
                                        Pageable pageable) {
        return engineFindService.findEngines(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public EngineRest findEngineById(@PathVariable("id") String id) {
        return engineFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    public Collection<EngineRest> findAllEngines() {
        return engineFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public EngineRest createEngine(@RequestBody EngineRest engine) {
        return engineSaveService.createPart(engine);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public EngineRest updateEngine(@RequestBody EngineRest engine) {
        return engineSaveService.updatePart(engine);
    }
}