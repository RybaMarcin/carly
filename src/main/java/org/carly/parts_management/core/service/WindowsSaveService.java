package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.WindowsRest;
import org.carly.parts_management.core.mapper.WindowsMapper;
import org.carly.parts_management.core.model.Windows;
import org.carly.parts_management.core.repository.WindowsRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WindowsSaveService implements PartSaveService<WindowsRest> {

    private final WindowsMapper windowsMapper;
    private final WindowsRepository windowsRepository;

    public WindowsSaveService(WindowsMapper windowsMapper,
                              WindowsRepository windowsRepository) {
        this.windowsMapper = windowsMapper;
        this.windowsRepository = windowsRepository;
    }


    @Override
    public WindowsRest createPart(WindowsRest part) {
        Windows windows = windowsMapper.simplifyDomainObject(part);
        windowsRepository.save(windows);
        log.info("Windows with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public WindowsRest updatePart(WindowsRest part) {
        Windows windowsToUpdate = windowsRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Windows updatedWindows = windowsMapper.mapToDomainObject(windowsToUpdate, part);
        windowsRepository.save(updatedWindows);
        log.info("Windows with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Windows windows = windowsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        windowsRepository.delete(windows);
        log.info("Windows with id: {} successfully deleted!", id);
    }
}
