package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EquipmentRest;
import org.carly.core.partsmanagement.mapper.EquipmentMapper;
import org.carly.core.partsmanagement.model.Equipment;
import org.carly.core.partsmanagement.repository.EquipmentRepository;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EquipmentSaveService implements PartSaveService<EquipmentRest> {

    private final EquipmentMapper equipmentMapper;
    private final EquipmentRepository equipmentRepository;


    public EquipmentSaveService(EquipmentMapper equipmentMapper,
                                EquipmentRepository equipmentRepository) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public EquipmentRest createPart(EquipmentRest part) {
        Equipment equipment = equipmentMapper.simplifyDomainObject(part);
        equipmentRepository.save(equipment);
        log.info("Equipment with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public EquipmentRest updatePart(EquipmentRest part) {
        Equipment equipmentToUpdate = equipmentRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Equipment updatedEquipment = equipmentMapper.mapToDomainObject(equipmentToUpdate, part);
        equipmentRepository.save(updatedEquipment);
        log.info("Equipment with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        equipmentRepository.delete(equipment);
        log.info("Equipment with id: {} successfully deleted!", id);
    }
}
