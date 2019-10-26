package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EquipmentRest;
import org.carly.parts_management.api.model.criteria.EquipmentSearchCriteriaRest;
import org.carly.parts_management.core.mapper.EquipmentMapper;
import org.carly.parts_management.core.model.Equipment;
import org.carly.parts_management.core.repository.EquipmentMongoRepository;
import org.carly.parts_management.core.repository.EquipmentRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EquipmentFindService implements PartFindService {

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

    @Override
    public Collection<EquipmentRest> findAll() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        log.info("Equipment list contains: {}", equipmentList.size());
        return equipmentList.stream().map(equipmentMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public EquipmentRest findPartById(ObjectId id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Equipment with id: {} was found!", id);
        return equipmentMapper.simplifyRestObject(equipment);
    }

    public Page<EquipmentRest> findEquipment(EquipmentSearchCriteriaRest searchCriteria, Pageable pageable) {
        return equipmentMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(equipmentMapper::simplifyRestObject);
    }

}
