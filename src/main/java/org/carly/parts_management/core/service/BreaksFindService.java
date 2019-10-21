package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.BreaksRest;
import org.carly.parts_management.api.model.BreaksSearchCriteriaRest;
import org.carly.parts_management.core.mapper.BreaksMapper;
import org.carly.parts_management.core.repository.BreaksRepository;
import org.carly.shared.service.part_services.PartFindService;
import org.carly.vehicle_management.core.model.Breaks;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public Collection findAll() {
        return null;
    }

    @Override
    public Breaks findPartById(ObjectId id) {
        return null;
    }


    public List<BreaksRest> findBreaks(BreaksSearchCriteriaRest searchCriteria, Page pageable) {
        return null;
    }

}
