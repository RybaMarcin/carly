package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EngineRest;
import org.carly.api.rest.partsmanagement.criteria.EngineSearchCriteriaRest;
import org.carly.core.partsmanagement.mapper.EngineMapper;
import org.carly.core.partsmanagement.repository.EngineMongoRepository;
import org.carly.core.partsmanagement.repository.EngineRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartFindService;
import org.carly.core.partsmanagement.model.Engine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EngineFindService implements PartFindService {


    private final EngineMapper engineMapper;
    private final EngineRepository engineRepository;
    private final EngineMongoRepository engineMongoRepository;


    public EngineFindService(EngineMapper engineMapper,
                             EngineRepository engineRepository,
                             EngineMongoRepository engineMongoRepository) {
        this.engineMapper = engineMapper;
        this.engineRepository = engineRepository;
        this.engineMongoRepository = engineMongoRepository;
    }

    @Override
    public Collection<EngineRest> findAll() {
        List<Engine> engineList = engineRepository.findAll();
        log.info("Engine list contains: {}", engineList.size());
        return engineList.stream().map(engineMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public EngineRest findPartById(ObjectId id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Engine with id: {} was found!", id);
        return engineMapper.simplifyRestObject(engine);
    }


    public Page<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria, Pageable pageable) {
        return engineMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(engineMapper::simplifyRestObject);
    }

}
