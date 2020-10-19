package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.WheelsRest;
import org.carly.api.rest.partsmanagement.criteria.WheelsSearchCriteriaRest;
import org.carly.core.partsmanagement.service.WheelsFindService;
import org.carly.core.partsmanagement.service.WheelsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/wheels")
@Slf4j
public class WheelsController {

    private final WheelsFindService wheelsFindService;
    private final WheelsSaveService wheelsSaveService;


    public WheelsController(WheelsFindService wheelsFindService,
                            WheelsSaveService wheelsSaveService) {
        this.wheelsFindService = wheelsFindService;
        this.wheelsSaveService = wheelsSaveService;
    }


    @GetMapping("/wheels")
    public Page<WheelsRest> findWheels(WheelsSearchCriteriaRest searchCriteria,
                                       Pageable pageable) {
        return wheelsFindService.findWheels(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public WheelsRest findWheelsById(@PathVariable("id") ObjectId id) {
        return wheelsFindService.findPartById(id);
    }

    @GetMapping("/all")
    public Collection<WheelsRest> findAllWheels() {
        return wheelsFindService.findAll();
    }

    @PostMapping()
    public WheelsRest createWheels(@RequestBody WheelsRest wheels) {
        return wheelsSaveService.createPart(wheels);
    }

    @PutMapping()
    public WheelsRest updateWheels(@RequestBody WheelsRest wheels) {
        return wheelsSaveService.updatePart(wheels);
    }



}
