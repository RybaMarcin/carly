package org.carly.parts_management.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EngineRest;
import org.carly.parts_management.api.model.EngineSearchCriteriaRest;
import org.carly.parts_management.core.service.EngineFindService;
import org.carly.parts_management.core.service.EngineSaveService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController("/engines")
@Slf4j
public class EngineController {

    private final EngineFindService engineFindService;
    private final EngineSaveService engineSaveService;

    public EngineController(EngineFindService engineFindService,
                            EngineSaveService engineSaveService) {

        this.engineFindService = engineFindService;
        this.engineSaveService = engineSaveService;

    }

    //todo: Finish implementation

    @GetMapping("/engines")
    public List<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria,
                                        Page pageable) {
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


    @PostMapping("/create-engine")
    public EngineRest createEngine(@RequestBody EngineRest engine) {
        return engineSaveService.createPart(engine);
    }

    @PutMapping()
    public EngineRest updateEngine(@RequestBody EngineRest engine) {
        return engineSaveService.updatePart(engine);
    }


}
