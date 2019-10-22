package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.BreaksRest;
import org.carly.parts_management.api.model.BreaksSearchCriteriaRest;
import org.carly.parts_management.core.mapper.BreaksMapper;
import org.carly.parts_management.core.repository.BreaksRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartFindService;
import org.carly.parts_management.core.model.Breaks;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BreaksFindService implements PartFindService {


    private final BreaksMapper breaksMapper;
    private final BreaksRepository breaksrepository;


    public BreaksFindService(BreaksMapper breaksMapper,
                             BreaksRepository breaksRepository) {
        this.breaksMapper = breaksMapper;
        this.breaksrepository = breaksRepository;
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


    public List<BreaksRest> findBreaks(BreaksSearchCriteriaRest searchCriteria, Page pageable) {
        return null;
    }

}
