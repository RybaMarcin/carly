package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.PaintingRest;
import org.carly.core.partsmanagement.mapper.PaintingMapper;
import org.carly.core.partsmanagement.model.Painting;
import org.carly.core.partsmanagement.repository.PaintingRepository;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class PaintingSaveService implements PartSaveService<PaintingRest> {

    private final PaintingMapper paintingMapper;
    private final PaintingRepository paintingRepository;


    public PaintingSaveService(PaintingMapper paintingMapper,
                               PaintingRepository paintingRepository) {
        this.paintingMapper = paintingMapper;
        this.paintingRepository = paintingRepository;
    }

    @Override
    public PaintingRest createPart(PaintingRest part) {
        Painting painting = paintingMapper.simplifyDomainObject(part);
        paintingRepository.save(painting);
        log.info("Painting with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public PaintingRest updatePart(PaintingRest part) {
        Painting paintingToUpdate = paintingRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Painting updatedPainting = paintingMapper.mapToDomainObject(paintingToUpdate, part);
        paintingRepository.save(updatedPainting);
        log.info("Painting with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Painting painting = paintingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        paintingRepository.delete(painting);
        log.info("Painting with id: {} successfully deleted!", id);
    }
}
