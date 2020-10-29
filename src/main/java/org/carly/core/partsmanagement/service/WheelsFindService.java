package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.criteria.WheelsSearchCriteriaRest;
import org.carly.core.partsmanagement.repository.WheelsMongoRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartFindService;
import org.carly.api.rest.partsmanagement.WheelsRest;
import org.carly.core.partsmanagement.mapper.WheelsMapper;
import org.carly.core.partsmanagement.model.Wheels;
import org.carly.core.partsmanagement.repository.WheelsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WheelsFindService implements PartFindService {

    private final WheelsMapper wheelsMapper;
    private final WheelsRepository wheelsRepository;
    private final WheelsMongoRepository wheelsMongoRepository;

    public WheelsFindService(WheelsMapper wheelsMapper,
                             WheelsRepository wheelsRepository,
                             WheelsMongoRepository wheelsMongoRepository) {
        this.wheelsMapper = wheelsMapper;
        this.wheelsRepository = wheelsRepository;
        this.wheelsMongoRepository = wheelsMongoRepository;
    }


    @Override
    public Collection<WheelsRest> findAll() {
        List<Wheels> wheelsList = wheelsRepository.findAll();
        log.info("Wheels list contains: {}", wheelsList.size());
        return wheelsList.stream().map(wheelsMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public WheelsRest findPartById(ObjectId id) {
        Wheels wheels = wheelsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Wheels with id: {} was found!", id);
        return wheelsMapper.simplifyRestObject(wheels);
    }


    public Page<WheelsRest> findWheels(WheelsSearchCriteriaRest searchCriteria, Pageable pageable) {
        return wheelsMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(wheelsMapper::simplifyRestObject);
    }

}
