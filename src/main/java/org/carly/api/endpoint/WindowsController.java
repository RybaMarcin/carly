package org.carly.api.endpoint;


import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.WindowsRest;
import org.carly.api.rest.partsmanagement.criteria.WindowsSearchCriteriaRest;
import org.carly.core.partsmanagement.service.WindowsFindService;
import org.carly.core.partsmanagement.service.WindowsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/windows")
public class WindowsController {

    private final WindowsFindService windowsFindService;
    private final WindowsSaveService windowsSaveService;

    public WindowsController(WindowsFindService windowsFindService,
                             WindowsSaveService windowsSaveService) {
        this.windowsFindService = windowsFindService;
        this.windowsSaveService = windowsSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<WindowsRest> findWindows(WindowsSearchCriteriaRest searchCriteria,
                                         Pageable pageable) {
        return windowsFindService.findWindows(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public WindowsRest findWindowsById(@PathVariable("id") String id) {
        return windowsFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public Collection<WindowsRest> findAllWindows() {
        return windowsFindService.findAll();
    }


    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_COMPANY')")
    public WindowsRest createWindows(@RequestBody WindowsRest windows) {
        return windowsSaveService.createPart(windows);
    }
}
