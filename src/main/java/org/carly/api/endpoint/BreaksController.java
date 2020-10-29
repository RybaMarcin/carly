package org.carly.api.endpoint;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.BreaksRest;
import org.carly.api.rest.partsmanagement.criteria.BreaksSearchCriteriaRest;
import org.carly.core.partsmanagement.service.BreaksFindService;
import org.carly.core.partsmanagement.service.BreaksSaveService;
import org.carly.core.security.service.LoggedUserProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/breaks")
@Slf4j
public class BreaksController {

    private final BreaksFindService breaksFindService;
    private final BreaksSaveService breaksSaveService;
    private final LoggedUserProvider loggedUserProvider;

    public BreaksController(BreaksFindService breaksFindService,
                            BreaksSaveService breaksSaveService,
                            LoggedUserProvider loggedUserProvider) {
        this.breaksFindService = breaksFindService;
        this.breaksSaveService = breaksSaveService;
        this.loggedUserProvider = loggedUserProvider;
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