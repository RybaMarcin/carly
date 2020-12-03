package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.TiresRequest;
import org.carly.api.rest.response.TiresResponse;
import org.carly.core.partsmanagement.mapper.TiresMapper;
import org.carly.core.partsmanagement.model.entity.Tires;
import org.carly.core.partsmanagement.repository.TiresRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class TiresSaveService {

    private final TiresMapper tiresMapper;
    private final TiresRepository tiresRepository;
    private final LoggedUserProvider loggedUserProvider;

    public TiresSaveService(TiresMapper tiresMapper,
                            TiresRepository tiresRepository,
                            LoggedUserProvider loggedUserProvider) {
        this.tiresMapper = tiresMapper;
        this.tiresRepository = tiresRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<TiresResponse> createPart(TiresRequest part) {
        Tires tires = tiresMapper.simplifyDomainObject(part);
        tires.setCreateBy(loggedUserProvider.loggedUser().getEmail());
        tires.setCreatedDate(LocalDateTime.now());
        tiresRepository.save(tires);
        log.info("Tires with id: {} successfully created!", part.getId());
        return ResponseEntity.ok(tiresMapper.mapFromDomainToResponse(tires));
    }

    public ResponseEntity<TiresResponse> updatePart(TiresRequest part) {
        Tires tiresToUpdate = tiresRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Tires updatedTires = tiresMapper.mapToDomainObject(tiresToUpdate, part);
        tiresRepository.save(updatedTires);
        log.info("Tires with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(tiresMapper.mapFromDomainToResponse(tiresToUpdate));
    }

    public void deletePart(ObjectId id) {
        Tires tires = tiresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        tiresRepository.delete(tires);
        log.info("Tires with id: {} successfully deleted!", id);
    }
}
