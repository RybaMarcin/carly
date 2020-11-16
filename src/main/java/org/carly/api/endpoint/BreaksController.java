package org.carly.api.endpoint;


import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.request.BreaksRequest;
import org.carly.api.rest.response.BreaksResponse;
import org.carly.core.partsmanagement.service.BreaksFindService;
import org.carly.core.partsmanagement.service.BreaksSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//breaks")
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
    public Page<BreaksResponse> findBreaks(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return breaksFindService.findBreaks(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public BreaksResponse findBreaksById(@PathVariable("id") String id) {
        return breaksFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<BreaksResponse> findAllBreaks() {
        return breaksFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public ResponseEntity<BreaksResponse> createBreaks(@RequestBody BreaksRequest breaks) {
        return breaksSaveService.createPart(breaks);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public ResponseEntity<BreaksResponse> updateBreaks(@RequestBody BreaksRequest breaks) {
        return breaksSaveService.updatePart(breaks);
    }
}