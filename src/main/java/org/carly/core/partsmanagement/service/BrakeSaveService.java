package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.core.partsmanagement.mapper.BrakeRequestMapper;
import org.carly.core.partsmanagement.mapper.BrakeResponseMapper;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.carly.core.partsmanagement.repository.BrakeRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BrakeSaveService {

    private final BrakeRequestMapper brakeRequestMapper;
    private final BrakeResponseMapper brakeResponseMapper;
    private final BrakeRepository brakeRepository;
    private final LoggedUserProvider loggedUserProvider;

    public BrakeSaveService(BrakeRequestMapper brakeRequestMapper,
                            BrakeResponseMapper brakeResponseMapper,
                            BrakeRepository brakeRepository,
                            LoggedUserProvider loggedUserProvider) {
        this.brakeRequestMapper = brakeRequestMapper;
        this.brakeResponseMapper = brakeResponseMapper;
        this.brakeRepository = brakeRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<BrakeResponse> createPart(BrakeRequest request) {
        Brake aBrake = brakeRequestMapper.simplifyDomainObject(request);
        aBrake.setCreatedDate(LocalDateTime.now());
        aBrake.setCreateBy(loggedUserProvider.loggedUser().getEmail());
        brakeRepository.save(aBrake);
        BrakeResponse response = brakeResponseMapper.mapFromRestToResponse(request);
        log.info("Breaks with id: {} successfully created!", request.getId());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BrakeResponse> updatePart(BrakeRequest part) {
        Brake brakeToUpdate = brakeRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Brake updatedBrake = brakeRequestMapper.mapToDomainObject(brakeToUpdate, part);
        brakeRepository.save(updatedBrake);
        BrakeResponse response = brakeResponseMapper.mapFromRestToResponse(part);
        log.info("Breaks with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<SuccessResponse> deleteBrake(String brakeId) {
        Brake aBrake = brakeRepository.findById(new ObjectId(brakeId))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        brakeRepository.delete(aBrake);
        log.info("Brake with id: {} successfully deleted!", aBrake.getId());
        return ResponseEntity.ok(new SuccessResponse("Brake successfully deleted: " + aBrake.getId()));
    }
}
