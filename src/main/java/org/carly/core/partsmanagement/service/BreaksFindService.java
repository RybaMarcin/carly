package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.BreaksRest;
import org.carly.api.rest.partsmanagement.criteria.BreaksSearchCriteriaRest;
import org.carly.core.partsmanagement.mapper.BreaksMapper;
import org.carly.core.partsmanagement.repository.BreaksMongoRepository;
import org.carly.core.partsmanagement.repository.BreaksRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.part_services.PartFindService;
import org.carly.core.partsmanagement.model.Breaks;
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


    private final BreaksMapper breaksMapper;
    private final BreaksRepository breaksrepository;
    private final BreaksMongoRepository breaksMongoRepository;


    public BreaksFindService(BreaksMapper breaksMapper,
                             BreaksRepository breaksRepository,
                             BreaksMongoRepository breaksMongoRepository) {
        this.breaksMapper = breaksMapper;
        this.breaksrepository = breaksRepository;
        this.breaksMongoRepository = breaksMongoRepository;
    }


    @Override
    public Collection<BreaksRest> findAll() {
        List<Breaks> breaksList = breaksrepository.findAll();
        log.info("Breaks list contains: {}", breaksList.size());
        return breaksList.stream().map(breaksMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public BreaksRest findPartById(ObjectId id) {
        Breaks breaks = breaksrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Breaks with id: {} was found!", id);
        return breaksMapper.simplifyRestObject(breaks);
    }


    public Page<BreaksRest> findBreaks(BreaksSearchCriteriaRest searchCriteria, Pageable pageable) {
        return breaksMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(breaksMapper::simplifyRestObject);
    }

}
