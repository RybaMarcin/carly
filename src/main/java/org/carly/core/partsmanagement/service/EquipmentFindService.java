package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.EquipmentSearchCriteriaRequest;
import org.carly.api.rest.response.EquipmentResponse;
import org.carly.core.partsmanagement.mapper.EquipmentMapper;
import org.carly.core.partsmanagement.model.entity.Equipment;
import org.carly.core.partsmanagement.repository.EquipmentMongoRepository;
import org.carly.core.partsmanagement.repository.EquipmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EquipmentFindService{

    private final EquipmentMapper equipmentMapper;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMongoRepository equipmentMongoRepository;

    public EquipmentFindService(EquipmentMapper equipmentMapper,
                                EquipmentRepository equipmentRepository,
                                EquipmentMongoRepository equipmentMongoRepository) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentRepository = equipmentRepository;
        this.equipmentMongoRepository = equipmentMongoRepository;
    }

    public Collection<EquipmentResponse> findAll() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        log.info("Equipment list contains: {}", equipmentList.size());
        return equipmentList.stream().map(equipmentMapper::mapFromDomainToResponse).collect(Collectors.toList());
    }

    public Page<EquipmentResponse> findEquipment(EquipmentSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return equipmentMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(equipmentMapper::mapFromDomainToResponse);
    }

    public Collection<EquipmentResponse> findAllEquipmentsByFactoryId(String factoryId) {
        Collection<Equipment> equipments = equipmentRepository.findAllByFactoryCarlyFactoryId(new ObjectId(factoryId));
        return equipments.stream().map(equipmentMapper::mapFromDomainToResponse).collect(Collectors.toList());

    }
}
