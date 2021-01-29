package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.WheelsSearchCriteriaRequest;
import org.carly.api.rest.response.WheelsResponse;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.carly.core.partsmanagement.mapper.WheelsResponseMapper;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.carly.core.partsmanagement.repository.WheelsMongoRepository;
import org.carly.core.partsmanagement.repository.WheelsRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WheelsFindService {

    private final WheelsResponseMapper wheelsResponseMapper;
    private final WheelsRepository wheelsRepository;
    private final WheelsMongoRepository wheelsMongoRepository;
    private final CompanyMatchingService companyMatchingService;

    public WheelsFindService(WheelsResponseMapper wheelsResponseMapper,
                             WheelsRepository wheelsRepository,
                             WheelsMongoRepository wheelsMongoRepository,
                             CompanyMatchingService companyMatchingService) {
        this.wheelsResponseMapper = wheelsResponseMapper;
        this.wheelsRepository = wheelsRepository;
        this.wheelsMongoRepository = wheelsMongoRepository;
        this.companyMatchingService = companyMatchingService;
    }

    public Collection<WheelsResponse> findAll() {
        List<Wheels> wheelsList = wheelsRepository.findAll();
        log.info("Wheels list contains: {}", wheelsList.size());
        return wheelsList.stream().map(wheelsResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public ResponseEntity<WheelsResponse> findPartById(ObjectId id) {
        Wheels wheels = wheelsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Wheels with id: {} was found!", id);
        return ResponseEntity.ok(wheelsResponseMapper.simplifyRestObject(wheels));
    }

    public Page<WheelsResponse> findWheels(WheelsSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return wheelsMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(wheelsResponseMapper::simplifyRestObject);
    }

    public Collection<WheelsResponse> findAllWheelsByFactoryId(String companyId) {
        Collection<Wheels> wheels = wheelsRepository.findAllByFactoryId(new ObjectId(companyId));
        log.info("Found: ({}) - wheels", wheels.size());
        return wheels.stream().map(wheelsResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public Collection<WheelsResponse> findAllWheelsAvailableForCompany(String companyId) {
        Collection<Wheels> availableWheels = wheelsMongoRepository.findWheelsWithFactoryIdInList(companyMatchingService.findMatchedFactoryIds(companyId));
        return availableWheels.stream().map(wheelsResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }
}