package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.BreaksRest;
import org.carly.parts_management.core.mapper.BreaksMapper;
import org.carly.parts_management.core.repository.BreaksRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartSaveService;
import org.carly.parts_management.core.model.Breaks;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BreaksSaveService implements PartSaveService<BreaksRest> {

    private final BreaksMapper breaksMapper;
    private final BreaksRepository breaksRepository;


    public BreaksSaveService(BreaksMapper breaksMapper,
                             BreaksRepository breaksRepository) {
        this.breaksMapper = breaksMapper;
        this.breaksRepository = breaksRepository;
    }


    @Override
    public BreaksRest createPart(BreaksRest part) {
        Breaks breaks = breaksMapper.simplifyDomainObject(part);
        breaksRepository.save(breaks);
        log.info("Breaks with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public BreaksRest updatePart(BreaksRest part) {
        Breaks breaksToUpdate = breaksRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Breaks updatedBreaks = breaksMapper.mapToDomainObject(breaksToUpdate, part);
        breaksRepository.save(updatedBreaks);
        log.info("Breaks with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Breaks breaks = breaksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        breaksRepository.delete(breaks);
        log.info("Breaks with id: {} successfully deleted!", id);
    }
}
