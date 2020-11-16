package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.PaintingRequest;
import org.carly.api.rest.criteria.PaintingSearchCriteriaRequest;
import org.carly.core.partsmanagement.service.PaintingFindService;
import org.carly.core.partsmanagement.service.PaintingSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//painting")
public class PaintingController {

    private final PaintingFindService paintingFindService;
    private final PaintingSaveService paintingSaveService;

    public PaintingController(PaintingFindService paintingFindService,
                              PaintingSaveService paintingSaveService) {
        this.paintingFindService = paintingFindService;
        this.paintingSaveService = paintingSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    private Page<PaintingRequest> findPaintings(PaintingSearchCriteriaRequest searchCriteria,
                                                Pageable pageable) {
        return paintingFindService.findPaintings(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public PaintingRequest findPaintingById(@PathVariable("id") String id) {
        return paintingFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<PaintingRequest> findAllPaintings() {
        return paintingFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public PaintingRequest createPainting(@RequestBody PaintingRequest painting) {
        return paintingSaveService.createPart(painting);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public PaintingRequest updatePainting(@RequestBody PaintingRequest painting) {
        return paintingSaveService.updatePart(painting);
    }
}