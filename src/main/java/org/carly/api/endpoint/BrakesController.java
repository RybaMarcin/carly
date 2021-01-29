package org.carly.api.endpoint;


import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.partsmanagement.service.BrakeFindService;
import org.carly.core.partsmanagement.service.BrakeSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/brakes")
public class BrakesController {


    private static final String FIND_BRAKES_BY_ID = "Find brakes by id";
    private static final String FIND_ALL_BRAKES = "Find all brakes";
    private static final String FIND_ALL_BRAKES_BY_FACTORY_ID = "Find all brakes by factory id";
    private static final String FIND_ALL_AVAILABLE_BRAKES_FOR_COMPANY = "Find all available brakes for company";
    private static final String CREATE_BRAKES = "Create new brakes";
    private static final String UPDATE_BRAKES = "Update brakes";
    private static final String DELETE_BRAKES_BY_ID = "Delete brakes by id";


    private final BrakeFindService brakeFindService;
    private final BrakeSaveService brakeSaveService;

    public BrakesController(BrakeFindService brakeFindService,
                            BrakeSaveService brakeSaveService) {
        this.brakeFindService = brakeFindService;
        this.brakeSaveService = brakeSaveService;
    }

    @GetMapping("/brakes")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<BrakeResponse> findBrakes(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return brakeFindService.findBreaks(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_BRAKES_BY_ID)
    public BrakeResponse findBrakeById(@PathVariable("id") String id) {
        return brakeFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all-breaks-by-factory-id/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_BRAKES_BY_FACTORY_ID)
    public Collection<BrakeResponse> findAllBreaksByFactoryId(@PathVariable(name = "factoryId") String factoryId) {
        return brakeFindService.findAllBreaksByFactoryId(factoryId);
    }

    @GetMapping("/all-brakes-for-company/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    @ApiOperation(value = FIND_ALL_AVAILABLE_BRAKES_FOR_COMPANY)
    public Collection<BrakeResponse> findAllBrakesAvailableForCompany(@PathVariable(name = "companyId") String companyId) {
        return brakeFindService.findAllBrakesAvailableForCompany(companyId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_ALL_BRAKES)
    public Collection<BrakeResponse> findAllBrakes() {
        return brakeFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    @ApiOperation(value = CREATE_BRAKES)
    public ResponseEntity<BrakeResponse> createBrake(@RequestBody BrakeRequest brakeRequest) {
        return brakeSaveService.createPart(brakeRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CARLY_FACTORY')")
    @ApiOperation(value = UPDATE_BRAKES)
    public ResponseEntity<BrakeResponse> updateBreak(@RequestBody BrakeRequest breaks) {
        return brakeSaveService.updatePart(breaks);
    }

    @DeleteMapping("/delete/{brakeId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_BRAKES_BY_ID)
    public ResponseEntity<SuccessResponse> deleteBrake(@PathVariable(name = "brakeId") String brakeId) {
        return brakeSaveService.deleteBrake(brakeId);
    }
}
