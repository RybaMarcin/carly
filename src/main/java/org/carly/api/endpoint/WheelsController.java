package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.WheelsSearchCriteriaRequest;
import org.carly.api.rest.request.WheelsRequest;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.api.rest.response.WheelsResponse;
import org.carly.core.partsmanagement.service.WheelsFindService;
import org.carly.core.partsmanagement.service.WheelsSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//wheels")
public class WheelsController {

    private static final String FIND_WHEELS_BY_ID = "Find wheels by id";
    private static final String FIND_ALL_WHEELS = "Find all wheels";
    private static final String FIND_ALL_WHEELS_BY_FACTORY_ID = "Find all wheels by factory id";
    private static final String FIND_ALL_AVAILABLE_WHEELS_FOR_COMPANY = "Find all available wheels for company";
    private static final String CREATE_WHEELS = "Create new wheels";
    private static final String UPDATE_WHEELS = "Update wheels";
    private static final String DELETE_WHEELS_BY_ID = "Delete wheels by id";

    private final WheelsFindService wheelsFindService;
    private final WheelsSaveService wheelsSaveService;

    public WheelsController(WheelsFindService wheelsFindService,
                            WheelsSaveService wheelsSaveService) {
        this.wheelsFindService = wheelsFindService;
        this.wheelsSaveService = wheelsSaveService;
    }

    @GetMapping("/wheels")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<WheelsResponse> findWheels(WheelsSearchCriteriaRequest searchCriteria,
                                           Pageable pageable) {
        return wheelsFindService.findWheels(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_WHEELS_BY_ID)
    public ResponseEntity<WheelsResponse> findWheelsById(@PathVariable("id") String id) {
        return wheelsFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_ALL_WHEELS)
    public Collection<WheelsResponse> findAllWheels() {
        return wheelsFindService.findAll();
    }

    @GetMapping("/all-breaks-by-factory-id/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_WHEELS_BY_FACTORY_ID)
    public Collection<WheelsResponse> findAllWheelsByFactoryId(@PathVariable(name = "factoryId") String factoryId) {
        return wheelsFindService.findAllWheelsByFactoryId(factoryId);
    }

    @GetMapping("/all-wheels-for-company/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    @ApiOperation(value = FIND_ALL_AVAILABLE_WHEELS_FOR_COMPANY)
    public Collection<WheelsResponse> findAllWheelsAvailableForCompany(@PathVariable(name = "companyId") String companyId) {
        return wheelsFindService.findAllWheelsAvailableForCompany(companyId);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    @ApiOperation(value = CREATE_WHEELS)
    public ResponseEntity<WheelsResponse> createWheels(@RequestBody WheelsRequest wheels) {
        return wheelsSaveService.createPart(wheels);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    @ApiOperation(value = UPDATE_WHEELS)
    public ResponseEntity<WheelsResponse> updateWheels(@RequestBody WheelsRequest wheels) {
        return wheelsSaveService.updatePart(wheels);
    }

    @DeleteMapping("/delete/{wheelsId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_WHEELS_BY_ID)
    public ResponseEntity<SuccessResponse> deleteWheels(@PathVariable(name = "wheelsId") String wheelsId) {
        return wheelsSaveService.deletePart(new ObjectId(wheelsId));
    }
}
