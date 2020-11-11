package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.WindowsSearchCriteriaRequest;
import org.carly.api.rest.response.WindowsResponse;
import org.carly.core.partsmanagement.mapper.WindowsResponseMapper;
import org.carly.core.partsmanagement.model.Windows;
import org.carly.core.partsmanagement.repository.WindowsMongoRepository;
import org.carly.core.partsmanagement.repository.WindowsRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WindowsFindService {

    private final WindowsResponseMapper windowsResponseMapper;
    private final WindowsRepository windowsRepository;
    private final WindowsMongoRepository windowsMongoRepository;

    public WindowsFindService(WindowsResponseMapper windowsResponseMapper,
                              WindowsRepository windowsRepository,
                              WindowsMongoRepository windowsMongoRepository) {
        this.windowsResponseMapper = windowsResponseMapper;
        this.windowsRepository = windowsRepository;
        this.windowsMongoRepository = windowsMongoRepository;
    }

    public Collection<WindowsResponse> findAll() {
        List<Windows> windowsList = windowsRepository.findAll();
        log.info("Windows list contains: {}", windowsList.size());
        return windowsList.stream().map(windowsResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public ResponseEntity<WindowsResponse> findPartById(ObjectId id) {
        Windows windows = windowsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Windows with id: {} found!", id);
        return ResponseEntity.ok(windowsResponseMapper.simplifyRestObject(windows));
    }

    public Page<WindowsResponse> findWindows(WindowsSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return windowsMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(windowsResponseMapper::simplifyRestObject);
    }
}