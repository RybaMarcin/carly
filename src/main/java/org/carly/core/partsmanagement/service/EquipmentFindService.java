package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EquipmentRest;
import org.carly.api.rest.partsmanagement.criteria.EquipmentSearchCriteriaRest;
import org.carly.core.partsmanagement.mapper.EquipmentMapper;
import org.carly.core.partsmanagement.model.Equipment;
import org.carly.core.partsmanagement.repository.EquipmentMongoRepository;
import org.carly.core.partsmanagement.repository.EquipmentRepository;
import org.carly.core.shared.service.part_services.PartFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public Object findPartById(ObjectId id) {
        return null;
    }

//    public EquipmentRest findPartById(String id) {
//        Equipment equipment = equipmentRepository.findById(ObjectId(id))
//                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
//        log.info("Equipment with id: {} was found!", id);
//        return equipmentMapper.simplifyRestObject(equipment);
//    }

    public Page<EquipmentRest> findEquipment(EquipmentSearchCriteriaRest searchCriteria, Pageable pageable) {
        return equipmentMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(equipmentMapper::simplifyRestObject);
    }

}
