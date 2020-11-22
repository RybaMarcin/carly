package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.EngineRequest;
import org.carly.api.rest.criteria.EngineSearchCriteriaRequest;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.partsmanagement.service.EngineFindService;
import org.carly.core.partsmanagement.service.EngineSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//engines")
public class EngineController {

    private final EngineFindService engineFindService;
    private final EngineSaveService engineSaveService;

    public EngineController(EngineFindService engineFindService,
                            EngineSaveService engineSaveService) {
        this.engineFindService = engineFindService;
        this.engineSaveService = engineSaveService;
    }

    @GetMapping("/engines")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<EngineResponse> findEngines(EngineSearchCriteriaRequest searchCriteria,
                                            Pageable pageable) {
        return engineFindService.findEngines(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<EngineResponse> findEngineById(@PathVariable("id") String id) {
        return engineFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public Collection<EngineResponse> findAllEngines() {
        return engineFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<EngineResponse> createEngine(@RequestBody EngineRequest engine) {
        return engineSaveService.createPart(engine);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<EngineResponse> updateEngine(@RequestBody EngineRequest engine) {
        return engineSaveService.updatePart(engine);
    }
}
