package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.EngineRequest;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.partsmanagement.mapper.EngineRequestMapper;
import org.carly.core.partsmanagement.mapper.EngineResponseMapper;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.partsmanagement.repository.EngineRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class EngineSaveService {

    private final EngineRequestMapper engineRequestMapper;
    private final EngineResponseMapper engineResponseMapper;
    private final EngineRepository engineRepository;

    public EngineSaveService(EngineRequestMapper engineRequestMapper,
                             EngineResponseMapper engineResponseMapper,
                             EngineRepository engineRepository) {
        this.engineRequestMapper = engineRequestMapper;
        this.engineResponseMapper = engineResponseMapper;
        this.engineRepository = engineRepository;
    }

    public ResponseEntity<EngineResponse> createPart(EngineRequest request) {
        Engine engine = engineRequestMapper.simplifyDomainObject(request);
        engineRepository.save(engine);
        log.info("Engine with id: {} successfully created!", request.getId());
        EngineResponse response = engineResponseMapper.simplifyRestObject(engine);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EngineResponse> updatePart(EngineRequest part) {
        Engine engineToUpdate = engineRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Engine updatedEngine = engineRequestMapper.mapToDomainObject(engineToUpdate, part);
        engineRepository.save(updatedEngine);
        log.info("Engine with id: {} successfully updated!", part.getId());
        EngineResponse response = engineResponseMapper.simplifyRestObject(engineToUpdate);
        return ResponseEntity.ok(response);
    }

    public void deletePart(ObjectId id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        engineRepository.delete(engine);
        log.info("Engine with id: {} successfully deleted!", id);
    }
}