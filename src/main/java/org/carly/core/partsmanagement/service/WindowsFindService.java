package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.WindowsRest;
import org.carly.api.rest.partsmanagement.criteria.WindowsSearchCriteriaRest;
import org.carly.core.partsmanagement.mapper.WindowsMapper;
import org.carly.core.partsmanagement.model.Windows;
import org.carly.core.partsmanagement.repository.WindowsMongoRepository;
import org.carly.core.partsmanagement.repository.WindowsRepository;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class WindowsFindService implements PartFindService {

    private final WindowsMapper windowsMapper;
    private final WindowsRepository windowsRepository;
    private final WindowsMongoRepository windowsMongoRepository;


    public WindowsFindService(WindowsMapper windowsMapper,
                              WindowsRepository windowsRepository,
                              WindowsMongoRepository windowsMongoRepository) {
        this.windowsMapper = windowsMapper;
        this.windowsRepository = windowsRepository;
        this.windowsMongoRepository = windowsMongoRepository;
    }


    @Override
    public Collection<WindowsRest> findAll() {
        List<Windows> windowsList = windowsRepository.findAll();
        log.info("Windows list contains: {}", windowsList.size());
        return windowsList.stream().map(windowsMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public WindowsRest findPartById(ObjectId id) {
        Windows windows = windowsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Windows with id: {} found!", id);
        return windowsMapper.simplifyRestObject(windows);
    }

    public Page<WindowsRest> findWindows(WindowsSearchCriteriaRest searchCriteria, Pageable pageable) {
        return windowsMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(windowsMapper::simplifyRestObject);
    }


}
