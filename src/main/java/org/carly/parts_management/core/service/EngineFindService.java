package org.carly.parts_management.core.service;

import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EngineRest;
import org.carly.parts_management.api.model.EngineSearchCriteriaRest;
import org.carly.parts_management.core.mapper.EngineMapper;
import org.carly.parts_management.core.repository.EngineRepository;
import org.carly.shared.service.part_services.PartFindService;
import org.carly.vehicle_management.core.model.Engine;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EngineFindService implements PartFindService {


    private final EngineMapper engineMapper;
    private final EngineRepository engineRepository;


    public EngineFindService(EngineMapper engineMapper,
                             EngineRepository engineRepository) {
        this.engineMapper = engineMapper;
        this.engineRepository = engineRepository;
    }



    @Override
    public Collection findAll() {
        return null;
    }

    @Override
    public Engine findPartById(ObjectId id) {
        return null;
    }


    public List<EngineRest> findEngines(EngineSearchCriteriaRest searchCriteria, Page pageable) {

        return null;
    }

}
