package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.PaintingRest;
import org.carly.api.rest.partsmanagement.criteria.PaintingSearchCriteriaRest;
import org.carly.core.partsmanagement.service.PaintingFindService;
import org.carly.core.partsmanagement.service.PaintingSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/painting")
@Slf4j
public class PaintingController {

    private final PaintingFindService paintingFindService;
    private final PaintingSaveService paintingSaveService;

    public PaintingController(PaintingFindService paintingFindService,
                              PaintingSaveService paintingSaveService) {
        this.paintingFindService = paintingFindService;
        this.paintingSaveService = paintingSaveService;
    }


    @GetMapping()
    private Page<PaintingRest> findPaintings(PaintingSearchCriteriaRest searchCriteria,
                                             Pageable pageable) {
        return paintingFindService.findPaintings(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public PaintingRest findPaintingById(@PathVariable("id") String id) {
        return paintingFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    public Collection<PaintingRest> findAllPaintings() {
        return paintingFindService.findAll();
    }

    @PostMapping()
    public PaintingRest createPainting(@RequestBody PaintingRest painting) {
        return paintingSaveService.createPart(painting);
    }

    @PutMapping()
    public PaintingRest updatePainting(@RequestBody PaintingRest painting) {
        return paintingSaveService.updatePart(painting);
    }

}
