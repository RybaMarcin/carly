package org.carly.parts_management.core.service;

import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.EngineRest;
import org.carly.parts_management.core.mapper.EngineMapper;
import org.carly.parts_management.core.repository.EngineRepository;
import org.carly.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

@Service
public class EngineSaveService implements PartSaveService<EngineRest> {

    private final EngineMapper engineMapper;
    private final EngineRepository engineRepository;


    public EngineSaveService(EngineMapper engineMapper,
                             EngineRepository engineRepository) {
        this.engineMapper = engineMapper;
        this.engineRepository = engineRepository;
    }


    //todo: Finish implementation

    @Override
    public EngineRest createPart(EngineRest part) {
        return null;
    }

    @Override
    public EngineRest updatePart(EngineRest part) {
        return null;
    }

    @Override
    public void deletePart(ObjectId id) {

    }
}
