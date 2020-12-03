package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.PaintingRequest;
import org.carly.api.rest.response.PaintingResponse;
import org.carly.core.partsmanagement.mapper.PaintingMapper;
import org.carly.core.partsmanagement.model.entity.Painting;
import org.carly.core.partsmanagement.repository.PaintingRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class PaintingSaveService {

    private final PaintingMapper paintingMapper;
    private final PaintingRepository paintingRepository;
    private final LoggedUserProvider loggedUserProvider;

    public PaintingSaveService(PaintingMapper paintingMapper,
                               PaintingRepository paintingRepository,
                               LoggedUserProvider loggedUserProvider) {
        this.paintingMapper = paintingMapper;
        this.paintingRepository = paintingRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<PaintingResponse> createPart(PaintingRequest part) {
        Painting painting = paintingMapper.simplifyDomainObject(part);
        painting.setCreateBy(loggedUserProvider.loggedUser().getEmail());
        painting.setCreatedDate(LocalDateTime.now());
        paintingRepository.save(painting);
        log.info("Painting with id: {} successfully created!", part.getId());
        return ResponseEntity.ok(paintingMapper.mapFromDomainToResponse(painting));
    }

    public ResponseEntity<PaintingResponse> updatePart(PaintingRequest part) {
        Painting paintingToUpdate = paintingRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Painting updatedPainting = paintingMapper.mapToDomainObject(paintingToUpdate, part);
        paintingRepository.save(updatedPainting);
        log.info("Painting with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(paintingMapper.mapFromDomainToResponse(paintingToUpdate));
    }

    public void deletePart(ObjectId id) {
        Painting painting = paintingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        paintingRepository.delete(painting);
        log.info("Painting with id: {} successfully deleted!", id);
    }
}
