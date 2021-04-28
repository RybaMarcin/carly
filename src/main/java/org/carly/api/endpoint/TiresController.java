package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.TiresSearchCriteriaRequest;
import org.carly.api.rest.request.TiresRequest;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.api.rest.response.TiresResponse;
import org.carly.core.partsmanagement.service.TiresFindService;
import org.carly.core.partsmanagement.service.TiresSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//tires")
public class TiresController {

    private static final String FIND_TIRES_BY_ID = "Find tires by id";
    private static final String FIND_ALL_TIRES = "Find all tires";
    private static final String FIND_ALL_TIRES_BY_FACTORY_ID = "Find all tires by factory id";
    private static final String FIND_ALL_AVAILABLE_TIRES_FOR_COMPANY = "Find all available tires for company";
    private static final String CREATE_TIRES = "Create new tires";
    private static final String UPDATE_TIRES = "Update tires";
    private static final String DELETE_TIRES_BY_ID = "Delete tires by id";

    private final TiresFindService tiresFindService;
    private final TiresSaveService tiresSaveService;

    public TiresController(TiresFindService tiresFindService,
                           TiresSaveService tiresSaveService) {
        this.tiresFindService = tiresFindService;
        this.tiresSaveService = tiresSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<TiresResponse> findTires(TiresSearchCriteriaRequest searchCriteria,
                                         Pageable pageable) {
        return tiresFindService.findTires(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_TIRES_BY_ID)
    public ResponseEntity<TiresResponse> findTiresById(@PathVariable("id") String id) {
        return tiresFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_ALL_TIRES)
    public Collection<TiresResponse> findAllTires() {
        return tiresFindService.findAll();
    }

    @GetMapping("/all-tires-by-factory-id/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_TIRES_BY_FACTORY_ID)
    public Collection<TiresResponse> findAllTiresByFactoryId(@PathVariable(name = "factoryId") String factoryId) {
        return null;
    }

    @GetMapping("/all-tires-for-company/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_AVAILABLE_TIRES_FOR_COMPANY)
    public Collection<TiresResponse> findAllTiresAvailableForCompany(@PathVariable(name = "companyId") String companyId) {
        return null;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = CREATE_TIRES)
    public ResponseEntity<TiresResponse> createTires(@RequestBody TiresRequest tires) {
        return tiresSaveService.createPart(tires);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = UPDATE_TIRES)
    public ResponseEntity<TiresResponse> updateTires(@RequestBody TiresRequest tires) {
        return tiresSaveService.updatePart(tires);
    }

    @DeleteMapping("/delete/{tiresId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_TIRES_BY_ID)
    public ResponseEntity<SuccessResponse> deleteTires(@PathVariable(name = "tiresId") String tiresId) {
        return tiresSaveService.deletePart(tiresId);
    }
}
