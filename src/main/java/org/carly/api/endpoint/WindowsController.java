package org.carly.api.endpoint;


import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.WindowsSearchCriteriaRequest;
import org.carly.api.rest.request.WindowsRequest;
import org.carly.api.rest.response.WindowsResponse;
import org.carly.core.partsmanagement.service.WindowsFindService;
import org.carly.core.partsmanagement.service.WindowsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//windows")
public class WindowsController {

    private final WindowsFindService windowsFindService;
    private final WindowsSaveService windowsSaveService;

    public WindowsController(WindowsFindService windowsFindService,
                             WindowsSaveService windowsSaveService) {
        this.windowsFindService = windowsFindService;
        this.windowsSaveService = windowsSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<WindowsResponse> findWindows(WindowsSearchCriteriaRequest searchCriteria,
                                             Pageable pageable) {
        return windowsFindService.findWindows(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<WindowsResponse> findWindowsById(@PathVariable("id") String id) {
        return windowsFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public Collection<WindowsResponse> findAllWindows() {
        return windowsFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    public ResponseEntity<WindowsResponse> createWindows(@RequestBody WindowsRequest windows) {
        return windowsSaveService.createPart(windows);
    }
}
