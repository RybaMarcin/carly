package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.TiresRest;
import org.carly.api.rest.partsmanagement.criteria.TiresSearchCriteriaRest;
import org.carly.core.partsmanagement.service.TiresFindService;
import org.carly.core.partsmanagement.service.TiresSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tires")
@Slf4j
public class TiresController {

    private final TiresFindService tiresFindService;
    private final TiresSaveService tiresSaveService;


    public TiresController(TiresFindService tiresFindService,
                           TiresSaveService tiresSaveService) {
        this.tiresFindService = tiresFindService;
        this.tiresSaveService = tiresSaveService;
    }

    @GetMapping()
    public Page<TiresRest> findTires(TiresSearchCriteriaRest searchCriteria,
                                     Pageable pageable) {
        return tiresFindService.findTires(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public TiresRest findTiresById(@PathVariable("id") ObjectId id) {
        return tiresFindService.findPartById(id);
    }

    @GetMapping("/all")
    public Collection<TiresRest> findAllTires() {
        return tiresFindService.findAll();
    }

    @PostMapping()
    public TiresRest createTires(@RequestBody TiresRest tires) {
        return tiresSaveService.createPart(tires);
    }

    @PutMapping()
    public TiresRest updateTires(@RequestBody TiresRest tires) {
        return tiresSaveService.updatePart(tires);
    }



}
