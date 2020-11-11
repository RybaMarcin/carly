package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.WindowsRequest;
import org.carly.api.rest.response.WindowsResponse;
import org.carly.core.partsmanagement.mapper.WindowsRequestMapper;
import org.carly.core.partsmanagement.mapper.WindowsResponseMapper;
import org.carly.core.partsmanagement.model.Windows;
import org.carly.core.partsmanagement.repository.WindowsRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WindowsSaveService {

    private final WindowsRequestMapper windowsRequestMapper;
    private final WindowsResponseMapper windowsResponseMapper;
    private final WindowsRepository windowsRepository;

    public WindowsSaveService(WindowsRequestMapper windowsRequestMapper,
                              WindowsResponseMapper windowsResponseMapper,
                              WindowsRepository windowsRepository) {
        this.windowsRequestMapper = windowsRequestMapper;
        this.windowsResponseMapper = windowsResponseMapper;
        this.windowsRepository = windowsRepository;
    }

    public ResponseEntity<WindowsResponse> createPart(WindowsRequest part) {
        Windows windows = windowsRequestMapper.simplifyDomainObject(part);
        windowsRepository.save(windows);
        log.info("Windows with id: {} successfully created!", part.getId());
        return ResponseEntity.ok(windowsResponseMapper.simplifyRestObject(windows));
    }

    public ResponseEntity<WindowsResponse> updatePart(WindowsRequest part) {
        Windows windowsToUpdate = windowsRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Windows updatedWindows = windowsRequestMapper.mapToDomainObject(windowsToUpdate, part);
        windowsRepository.save(updatedWindows);
        log.info("Windows with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(windowsResponseMapper.simplifyRestObject(windowsToUpdate));
    }

    public void deletePart(ObjectId id) {
        Windows windows = windowsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        windowsRepository.delete(windows);
        log.info("Windows with id: {} successfully deleted!", id);
    }
}
