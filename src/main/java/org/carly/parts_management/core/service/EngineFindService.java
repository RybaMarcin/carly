package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EngineRest;
import org.carly.parts_management.api.model.EngineSearchCriteriaRest;
import org.carly.parts_management.core.mapper.EngineMapper;
import org.carly.parts_management.core.repository.EngineRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartFindService;
import org.carly.parts_management.core.model.Engine;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EngineFindService implements PartFindService {


    private final EngineMapper engineMapper;
    private final EngineRepository engineRepository;


    public EngineFindService(EngineMapper engineMapper,
                             EngineRepository engineRepository) {
        this.engineMapper = engineMapper;
        this.engineRepository = engineRepository;
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


    public List<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria, Page pageable) {

        return null;
    }

}
