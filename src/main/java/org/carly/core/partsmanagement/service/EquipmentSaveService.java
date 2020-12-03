package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.EquipmentRequest;
import org.carly.api.rest.response.EquipmentResponse;
import org.carly.core.partsmanagement.mapper.EquipmentMapper;
import org.carly.core.partsmanagement.model.entity.Equipment;
import org.carly.core.partsmanagement.repository.EquipmentRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EquipmentSaveService {

    private final EquipmentMapper equipmentMapper;
    private final EquipmentRepository equipmentRepository;
    private final LoggedUserProvider loggedUserProvider;

    public EquipmentSaveService(EquipmentMapper equipmentMapper,
                                EquipmentRepository equipmentRepository,
                                LoggedUserProvider loggedUserProvider) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentRepository = equipmentRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<EquipmentResponse> createPart(EquipmentRequest equipmentRequest) {
        Equipment equipment = equipmentMapper.simplifyDomainObject(equipmentRequest);
        equipment.setCreateBy(loggedUserProvider.loggedUser().getEmail());
        equipment.setCreatedDate(LocalDateTime.now());
        equipmentRepository.save(equipment);
        log.info("Equipment with id: {} successfully created!", equipmentRequest.getId());
        return ResponseEntity.ok(equipmentMapper.mapFromDomainToResponse(equipment));
    }

    public ResponseEntity<EquipmentResponse> updatePart(EquipmentRequest part) {
        Equipment equipmentToUpdate = equipmentRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Equipment updatedEquipment = equipmentMapper.mapToDomainObject(equipmentToUpdate, part);
        equipmentRepository.save(updatedEquipment);
        log.info("Equipment with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(equipmentMapper.mapFromDomainToResponse(equipmentToUpdate));
    }

    public void deletePart(ObjectId id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        equipmentRepository.delete(equipment);
        log.info("Equipment with id: {} successfully deleted!", id);
    }
}
