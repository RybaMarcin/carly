package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.EngineSearchCriteriaRequest;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.carly.core.companymanagement.model.CompanyMatchStatus;
import org.carly.core.companymanagement.repository.CompanyMatchRepository;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.carly.core.partsmanagement.mapper.EngineResponseMapper;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.partsmanagement.repository.EngineMongoRepository;
import org.carly.core.partsmanagement.repository.EngineRepository;
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
public class EngineFindService  {

    private final EngineResponseMapper engineResponseMapper;
    private final EngineRepository engineRepository;
    private final EngineMongoRepository engineMongoRepository;
    private final CompanyMatchingService companyMatchingService;

    public EngineFindService(EngineResponseMapper engineResponseMapper,
                             EngineRepository engineRepository,
                             EngineMongoRepository engineMongoRepository,
                             CompanyMatchingService companyMatchingService) {
        this.engineResponseMapper = engineResponseMapper;
        this.engineRepository = engineRepository;
        this.engineMongoRepository = engineMongoRepository;
        this.companyMatchingService = companyMatchingService;
    }

    public Collection<EngineResponse> findAll() {
        List<Engine> engineList = engineRepository.findAll();
        log.info("Engine list contains: {}", engineList.size());
        return engineList.stream().map(engineResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public ResponseEntity<EngineResponse> findPartById(ObjectId id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Engine with id: {} was found!", id);
        return ResponseEntity.ok(engineResponseMapper.simplifyRestObject(engine));
    }

    public Page<EngineResponse> findEngines(EngineSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return engineMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(engineResponseMapper::simplifyRestObject);
    }

    public Collection<EngineResponse> findAllEnginesByFactoryId(String factoryId) {
        Collection<Engine> engines = engineRepository.findAllByFactoryCarlyFactoryId(new ObjectId(factoryId));
        return engines.stream().map(engineResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public Collection<EngineResponse> findAllEnginesAvailableForCompany(String companyId) {
        Collection<Engine> availableEngines = engineMongoRepository.findEnginesWithFactoryIdInList(companyMatchingService.findMatchedFactoryIds(companyId));
        return availableEngines.stream().map(engineResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

}