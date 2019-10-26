package org.carly.parts_management.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EquipmentRest;
import org.carly.parts_management.api.model.criteria.EquipmentSearchCriteriaRest;
import org.carly.parts_management.core.service.EquipmentFindService;
import org.carly.parts_management.core.service.EquipmentSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/equipment")
@Slf4j
public class EquipmentController {

    private final EquipmentFindService equipmentFindService;
    private final EquipmentSaveService equipmentSaveService;


    public EquipmentController(EquipmentFindService equipmentFindService,
                               EquipmentSaveService equipmentSaveService) {
        this.equipmentFindService = equipmentFindService;
        this.equipmentSaveService = equipmentSaveService;
    }


    @GetMapping()
    public Page<EquipmentRest> findEquipment(EquipmentSearchCriteriaRest searchCriteria,
                                             Pageable pageable) {
        return equipmentFindService.findEquipment(searchCriteria, pageable);
    }

    @GetMapping("/{id}")
    public EquipmentRest findEquipmentById(@PathVariable("id") ObjectId id) {
        return equipmentFindService.findPartById(id);
    }

    @GetMapping("/all")
    public Collection<EquipmentRest> findAllEquipment() {
        return equipmentFindService.findAll();
    }

    @PostMapping()
    public EquipmentRest createEquipment(@RequestBody EquipmentRest equipment) {
        return equipmentSaveService.createPart(equipment);
    }

    @PutMapping()
    public EquipmentRest updateEquipment(@RequestBody EquipmentRest equipment) {
        return equipmentSaveService.updatePart(equipment);
    }

}
