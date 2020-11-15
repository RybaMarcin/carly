package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.response.BreaksResponse;
import org.carly.core.partsmanagement.mapper.BreaksResponseMapper;
import org.carly.core.partsmanagement.repository.BreaksMongoRepository;
import org.carly.core.partsmanagement.repository.BreaksRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartFindService;
import org.carly.core.partsmanagement.model.entity.Breaks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BreaksFindService implements PartFindService {


    private final BreaksResponseMapper breaksResponseMapper;
    private final BreaksRepository breaksrepository;
    private final BreaksMongoRepository breaksMongoRepository;


    public BreaksFindService(BreaksResponseMapper breaksResponseMapper,
                             BreaksRepository breaksRepository,
                             BreaksMongoRepository breaksMongoRepository) {
        this.breaksResponseMapper = breaksResponseMapper;
        this.breaksrepository = breaksRepository;
        this.breaksMongoRepository = breaksMongoRepository;
    }


    @Override
    public Collection<BreaksResponse> findAll() {
        List<Breaks> breaksList = breaksrepository.findAll();
        log.info("Breaks list contains: {}", breaksList.size());
        return breaksList.stream().map(breaksResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public BreaksResponse findPartById(ObjectId id) {
        Breaks breaks = breaksrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Breaks with id: {} was found!", id);
        return breaksResponseMapper.simplifyRestObject(breaks);
    }


    public Page<BreaksResponse> findBreaks(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return breaksMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(breaksResponseMapper::simplifyRestObject);
    }

}
