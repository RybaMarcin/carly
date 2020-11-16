package org.carly.api.endpoint;

import org.carly.api.rest.request.EquipmentRest;
import org.carly.api.rest.criteria.EquipmentSearchCriteriaRequest;
import org.carly.core.partsmanagement.service.EquipmentFindService;
import org.carly.core.partsmanagement.service.EquipmentSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/api//equipment")
public class EquipmentController {

    private final EquipmentFindService equipmentFindService;
    private final EquipmentSaveService equipmentSaveService;

    public EquipmentController(EquipmentFindService equipmentFindService,
                               EquipmentSaveService equipmentSaveService) {
        this.equipmentFindService = equipmentFindService;
        this.equipmentSaveService = equipmentSaveService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<EquipmentRest> findEquipment(EquipmentSearchCriteriaRequest searchCriteria,
                                             Pageable pageable) {
        return equipmentFindService.findEquipment(searchCriteria, pageable);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<EquipmentRest> findAllEquipment() {
        return equipmentFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public EquipmentRest createEquipment(@RequestBody EquipmentRest equipment) {
        return equipmentSaveService.createPart(equipment);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public EquipmentRest updateEquipment(@RequestBody EquipmentRest equipment) {
        return equipmentSaveService.updatePart(equipment);
    }
}