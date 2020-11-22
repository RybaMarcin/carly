package org.carly.api.endpoint;

import org.carly.api.rest.request.EquipmentRequest;
import org.carly.api.rest.response.EquipmentResponse;
import org.carly.api.rest.criteria.EquipmentSearchCriteriaRequest;
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

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<EquipmentResponse> findAllEquipment() {
        return equipmentFindService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        return equipmentSaveService.createPart(equipmentRequest);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_FACTORY')")
    public ResponseEntity<EquipmentResponse> updateEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        return equipmentSaveService.updatePart(equipmentRequest);
    }
}
