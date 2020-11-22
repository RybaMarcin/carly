package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.WindowsRequest;
import org.carly.api.rest.response.WindowsResponse;
import org.carly.core.partsmanagement.mapper.WindowsRequestMapper;
import org.carly.core.partsmanagement.mapper.WindowsResponseMapper;
import org.carly.core.partsmanagement.model.entity.Windows;
import org.carly.core.partsmanagement.repository.WindowsRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WindowsSaveService {

    private final WindowsRequestMapper windowsRequestMapper;
    private final WindowsResponseMapper windowsResponseMapper;
    private final WindowsRepository windowsRepository;
    private final LoggedUserProvider loggedUserProvider;

    public WindowsSaveService(WindowsRequestMapper windowsRequestMapper,
                              WindowsResponseMapper windowsResponseMapper,
                              WindowsRepository windowsRepository,
                              LoggedUserProvider loggedUserProvider) {
        this.windowsRequestMapper = windowsRequestMapper;
        this.windowsResponseMapper = windowsResponseMapper;
        this.windowsRepository = windowsRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<WindowsResponse> createPart(WindowsRequest part) {
        Windows windows = windowsRequestMapper.simplifyDomainObject(part);
        windows.setCreateBy(loggedUserProvider.provideUserDetail().getEmail());
        windows.setCreatedDate(LocalDateTime.now());
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
