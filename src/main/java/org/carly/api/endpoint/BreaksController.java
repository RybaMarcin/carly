package org.carly.api.endpoint;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.BreaksRest;
import org.carly.api.rest.partsmanagement.criteria.BreaksSearchCriteriaRest;
import org.carly.core.partsmanagement.service.BreaksFindService;
import org.carly.core.partsmanagement.service.BreaksSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/breaks")
@Slf4j
public class BreaksController {

    private final BreaksFindService breaksFindService;
    private final BreaksSaveService breaksSaveService;


    public BreaksController(BreaksFindService breaksFindService,
                            BreaksSaveService breaksSaveService) {
        this.breaksFindService = breaksFindService;
        this.breaksSaveService = breaksSaveService;
    }

    @GetMapping("/breaks")
    public Page<BreaksRest> findBreaks(BreaksSearchCriteriaRest searchCriteria, Pageable pageable) {
        return breaksFindService.findBreaks(searchCriteria, pageable);
    }


    @GetMapping("/{id}")
    public BreaksRest findBreaksById(@PathVariable("id") ObjectId id) {
        return breaksFindService.findPartById(id);
    }


    @GetMapping("/all")
    public Collection<BreaksRest> findAllBreaks() {
        return breaksFindService.findAll();
    }

    @PostMapping()
    public BreaksRest createBreaks(@RequestBody BreaksRest breaks) {
        return breaksSaveService.createPart(breaks);
    }

    @PutMapping
    public BreaksRest updateBreaks(@RequestBody BreaksRest breaks) {
        return breaksSaveService.updatePart(breaks);
    }


}
