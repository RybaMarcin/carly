package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.EngineRequest;
import org.carly.api.rest.criteria.EngineSearchCriteriaRequest;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.partsmanagement.service.EngineFindService;
import org.carly.core.partsmanagement.service.EngineSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api//engines")
public class EngineController {

    private static final String FIND_ENGINE_BY_ID = "Find engine by id";
    private static final String FIND_ALL_ENGINES = "Find all engines";
    private static final String FIND_ALL_ENGINES_BY_FACTORY_ID = "Find all engines by factory id";
    private static final String FIND_ALL_AVAILABLE_ENGINES_FOR_COMPANY = "Find all available engines for company";
    private static final String CREATE_ENGINE = "Create new engine";
    private static final String UPDATE_ENGINE = "Update engine";
    private static final String DELETE_ENGINE_BY_ID = "Delete engine by id";

    private final EngineFindService engineFindService;
    private final EngineSaveService engineSaveService;

    public EngineController(EngineFindService engineFindService,
                            EngineSaveService engineSaveService) {
        this.engineFindService = engineFindService;
        this.engineSaveService = engineSaveService;
    }

    @GetMapping("/engines")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<EngineResponse> findEngines(EngineSearchCriteriaRequest searchCriteria,
                                            Pageable pageable) {
        return engineFindService.findEngines(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    @ApiOperation(value = FIND_ENGINE_BY_ID)
    public ResponseEntity<EngineResponse> findEngineById(@PathVariable("id") String id) {
        return engineFindService.findPartById(new ObjectId(id));
    }

    @GetMapping("/all-engines-by-factory-id/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_ENGINES_BY_FACTORY_ID)
    public Collection<EngineResponse> findAllEnginesByFactoryId(@PathVariable(name = "factoryId") String factoryId) {
        return engineFindService.findAllEnginesByFactoryId(factoryId);
    }

    @GetMapping("/all-engines-for-company/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    @ApiOperation(value = FIND_ALL_AVAILABLE_ENGINES_FOR_COMPANY)
    public Collection<EngineResponse> findAllEnginesAvailableForCompany(@PathVariable(name = "companyId") String companyId) {
        return engineFindService.findAllEnginesAvailableForCompany(companyId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = FIND_ALL_ENGINES)
    public Collection<EngineResponse> findAllEngines() {
        return engineFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = CREATE_ENGINE)
    public ResponseEntity<EngineResponse> createEngine(@RequestBody EngineRequest engine) {
        return engineSaveService.createPart(engine);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = UPDATE_ENGINE)
    public ResponseEntity<EngineResponse> updateEngine(@RequestBody EngineRequest engine) {
        return engineSaveService.updatePart(engine);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_ENGINE_BY_ID)
    public ResponseEntity<?> deleteEngine(@PathVariable(name = "id") String id) {
        return engineSaveService.deletePart(id);
    }

}
