package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.criteria.EquipmentSearchCriteriaRequest;
import org.carly.api.rest.request.EquipmentRequest;
import org.carly.api.rest.response.EquipmentResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.partsmanagement.service.EquipmentFindService;
import org.carly.core.partsmanagement.service.EquipmentSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/api//equipment")
public class EquipmentController {

    private static final String FIND_EQUIPMENT_BY_ID = "Find equipment by id";
    private static final String FIND_ALL_EQUIPMENT = "Find all equipment";
    private static final String FIND_ALL_EQUIPMENT_BY_FACTORY_ID = "Find all equipment by factory id";
    private static final String FIND_ALL_AVAILABLE_EQUIPMENT_FOR_COMPANY = "Find all available equipment for company";
    private static final String CREATE_EQUIPMENT = "Create new equipment";
    private static final String UPDATE_EQUIPMENT = "Update equipment";
    private static final String DELETE_EQUIPMENT_BY_ID = "Delete equipment by id";

    private final EquipmentFindService equipmentFindService;
    private final EquipmentSaveService equipmentSaveService;

    public EquipmentController(EquipmentFindService equipmentFindService,
                               EquipmentSaveService equipmentSaveService) {
        this.equipmentFindService = equipmentFindService;
        this.equipmentSaveService = equipmentSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<EquipmentResponse> findEquipment(EquipmentSearchCriteriaRequest searchCriteria,
                                                 Pageable pageable) {
        return equipmentFindService.findEquipment(searchCriteria, pageable);
    }

    @GetMapping("/all-equipment-by-factory-id/{factoryId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public Collection<EquipmentResponse> findAllEquipmentsByCompanyId(@PathVariable(name = "factoryId") String factoryId) {
        return equipmentFindService.findAllEquipmentsByFactoryId(factoryId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<EquipmentResponse> findAllEquipment() {
        return equipmentFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = CREATE_EQUIPMENT)
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        return equipmentSaveService.createPart(equipmentRequest);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = UPDATE_EQUIPMENT)
    public ResponseEntity<EquipmentResponse> updateEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        return equipmentSaveService.updatePart(equipmentRequest);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    @ApiOperation(value = DELETE_EQUIPMENT_BY_ID)
    public ResponseEntity<SuccessResponse> deleteEquipment(String equipmentId) {
        return equipmentSaveService.deletePart(equipmentId);
    }
}
