package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EngineRest;
import org.carly.api.rest.partsmanagement.criteria.EngineSearchCriteriaRest;
import org.carly.core.partsmanagement.service.EngineFindService;
import org.carly.core.partsmanagement.service.EngineSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/engines")
@Slf4j
public class EngineController {

    private final EngineFindService engineFindService;
    private final EngineSaveService engineSaveService;

    public EngineController(EngineFindService engineFindService,
                            EngineSaveService engineSaveService) {

        this.engineFindService = engineFindService;
        this.engineSaveService = engineSaveService;

    }


    @GetMapping("/engines")
    public Page<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria,
                                        Pageable pageable) {
        return engineFindService.findEngines(searchCriteria, pageable);
    }


    @GetMapping("/{id}")
    public EngineRest findEngineById(@PathVariable("id") ObjectId id) {
        return engineFindService.findPartById(id);
    }


    @GetMapping("/all")
    public Collection<EngineRest> findAllEngines() {
        return engineFindService.findAll();
    }


    @PostMapping()
    public EngineRest createEngine(@RequestBody EngineRest engine) {
        return engineSaveService.createPart(engine);
    }

    @PutMapping()
    public EngineRest updateEngine(@RequestBody EngineRest engine) {
        return engineSaveService.updatePart(engine);
    }


}
