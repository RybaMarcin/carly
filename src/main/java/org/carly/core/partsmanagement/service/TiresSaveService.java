package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.TiresRest;
import org.carly.core.partsmanagement.mapper.TiresMapper;
import org.carly.core.partsmanagement.model.Tires;
import org.carly.core.partsmanagement.repository.TiresRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class TiresSaveService implements PartSaveService<TiresRest> {

    private final TiresMapper tiresMapper;
    private final TiresRepository tiresRepository;

    public TiresSaveService(TiresMapper tiresMapper,
                            TiresRepository tiresRepository) {
        this.tiresMapper = tiresMapper;
        this.tiresRepository = tiresRepository;
    }

    @Override
    public TiresRest createPart(TiresRest part) {
        Tires tires = tiresMapper.simplifyDomainObject(part);
        tiresRepository.save(tires);
        log.info("Tires with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public TiresRest updatePart(TiresRest part) {
        Tires tiresToUpdate = tiresRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Tires updatedTires = tiresMapper.mapToDomainObject(tiresToUpdate, part);
        tiresRepository.save(updatedTires);
        log.info("Tires with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Tires tires = tiresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        tiresRepository.delete(tires);
        log.info("Tires with id: {} successfully deleted!", id);
    }
}
