package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.WheelsSearchCriteriaRest;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartFindService;
import org.carly.parts_management.api.model.WheelsRest;
import org.carly.parts_management.core.mapper.WheelsMapper;
import org.carly.vehicle_management.core.model.Wheels;
import org.carly.parts_management.core.repository.WheelsRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WheelsFindService implements PartFindService {

    private final WheelsMapper wheelsMapper;
    private final WheelsRepository wheelsRepository;

    public WheelsFindService(WheelsMapper wheelsMapper,
                             WheelsRepository wheelsRepository) {
        this.wheelsMapper = wheelsMapper;
        this.wheelsRepository = wheelsRepository;
    }


    @Override
    public Collection<WheelsRest> findAll() {
        List<Wheels> wheelsList = wheelsRepository.findAll();

        return wheelsList.stream().map(wheelsMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public Wheels findPartById(ObjectId id) {
        Wheels wheels = wheelsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return wheels;
    }


    public List<WheelsRest> findWheels(WheelsSearchCriteriaRest searchCriteria, Page pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", searchCriteria.getNames());
        params.put("code", searchCriteria.getWheelsCodes());
        params.put("brand", searchCriteria.getBrandNames());
        return null;
    }

}
