package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.WheelsRequest;
import org.carly.api.rest.response.WheelsResponse;
import org.carly.core.partsmanagement.mapper.WheelsRequestMapper;
import org.carly.core.partsmanagement.mapper.WheelsResponseMapper;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.carly.core.partsmanagement.repository.WheelsRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WheelsSaveService {

    private final WheelsRequestMapper wheelsRequestMapper;
    private final WheelsResponseMapper wheelsResponseMapper;
    private final WheelsRepository wheelsRepository;

    public WheelsSaveService(WheelsRequestMapper wheelsRequestMapper,
                             WheelsResponseMapper wheelsResponseMapper,
                             WheelsRepository wheelsRepository) {
        this.wheelsRequestMapper = wheelsRequestMapper;
        this.wheelsResponseMapper = wheelsResponseMapper;
        this.wheelsRepository = wheelsRepository;
    }

    public ResponseEntity<WheelsResponse> createPart(WheelsRequest part) {
        Wheels wheels = wheelsRequestMapper.simplifyDomainObject(part);
        wheelsRepository.save(wheels);
        log.info("Wheels with id: {} successfully created!", part.getId());
        WheelsResponse response = wheelsResponseMapper.simplifyRestObject(wheels);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<WheelsResponse> updatePart(WheelsRequest part) {
        Wheels wheelsToUpdate = wheelsRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Wheels updatedWheels = wheelsRequestMapper.mapToDomainObject(wheelsToUpdate, part);
        wheelsRepository.save(updatedWheels);
        log.info("Wheels with id: {} successfully updated!", part.getId());
        WheelsResponse response = wheelsResponseMapper.simplifyRestObject(wheelsToUpdate);
        return ResponseEntity.ok(response);
    }

    public void deletePart(ObjectId id) {
        Wheels wheels = wheelsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        wheelsRepository.delete(wheels);
        log.info("Wheels with id: {} successfully deleted!", id);
    }
}