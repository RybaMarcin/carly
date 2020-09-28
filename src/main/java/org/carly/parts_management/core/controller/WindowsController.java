package org.carly.parts_management.core.controller;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.WindowsRest;
import org.carly.parts_management.api.model.criteria.WindowsSearchCriteriaRest;
import org.carly.parts_management.core.service.WindowsFindService;
import org.carly.parts_management.core.service.WindowsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/windows")
@Slf4j
public class WindowsController {

    private final WindowsFindService windowsFindService;
    private final WindowsSaveService windowsSaveService;

    public WindowsController(WindowsFindService windowsFindService,
                             WindowsSaveService windowsSaveService) {
        this.windowsFindService = windowsFindService;
        this.windowsSaveService = windowsSaveService;
    }

    @GetMapping()
    public Page<WindowsRest> findWindows(WindowsSearchCriteriaRest searchCriteria,
                                         Pageable pageable) {
        return windowsFindService.findWindows(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public WindowsRest findWindowsById(@PathVariable("id") ObjectId id) {
        return windowsFindService.findPartById(id);
    }


    @GetMapping("/all")
    public Collection<WindowsRest> findAllWindows() {
        return windowsFindService.findAll();
    }


    @PostMapping()
    public WindowsRest createWindows(@RequestBody WindowsRest windows) {
        return windowsSaveService.createPart(windows);
    }



}
