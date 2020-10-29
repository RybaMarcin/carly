package org.carly.api.endpoint;


import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.BreaksRest;
import org.carly.api.rest.partsmanagement.criteria.BreaksSearchCriteriaRest;
import org.carly.core.partsmanagement.service.BreaksFindService;
import org.carly.core.partsmanagement.service.BreaksSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/breaks")
public class BreaksController {

    private final BreaksFindService breaksFindService;
    private final BreaksSaveService breaksSaveService;

    public BreaksController(BreaksFindService breaksFindService,
                            BreaksSaveService breaksSaveService) {
        this.breaksFindService = breaksFindService;
        this.breaksSaveService = breaksSaveService;
    }

    @GetMapping("/breaks")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<BreaksRest> findBreaks(BreaksSearchCriteriaRest searchCriteria, Pageable pageable) {
        return breaksFindService.findBreaks(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public BreaksRest findBreaksById(@PathVariable("id") String id) {
        return breaksFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<BreaksRest> findAllBreaks() {
        return breaksFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public BreaksRest createBreaks(@RequestBody BreaksRest breaks) {
        return breaksSaveService.createPart(breaks);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public BreaksRest updateBreaks(@RequestBody BreaksRest breaks) {
        return breaksSaveService.updatePart(breaks);
    }
}