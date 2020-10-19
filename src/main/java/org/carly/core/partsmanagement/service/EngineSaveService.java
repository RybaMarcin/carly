package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.EngineRest;
import org.carly.core.partsmanagement.mapper.EngineMapper;
import org.carly.core.partsmanagement.model.Engine;
import org.carly.core.partsmanagement.repository.EngineRepository;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EngineSaveService implements PartSaveService<EngineRest> {

    private final EngineMapper engineMapper;
    private final EngineRepository engineRepository;


    public EngineSaveService(EngineMapper engineMapper,
                             EngineRepository engineRepository) {
        this.engineMapper = engineMapper;
        this.engineRepository = engineRepository;
    }


    @Override
    public EngineRest createPart(EngineRest part) {
        Engine engine = engineMapper.simplifyDomainObject(part);
        engineRepository.save(engine);
        log.info("Engine with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public EngineRest updatePart(EngineRest part) {
        Engine engineToUpdate = engineRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Engine updatedEngine = engineMapper.mapToDomainObject(engineToUpdate, part);
        engineRepository.save(updatedEngine);
        log.info("Engine with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        engineRepository.delete(engine);
        log.info("Engine with id: {} successfully deleted!", id);
    }
}
