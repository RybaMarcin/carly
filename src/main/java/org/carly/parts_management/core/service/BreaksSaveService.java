package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.BreaksRest;
import org.carly.parts_management.core.mapper.BreaksMapper;
import org.carly.parts_management.core.repository.BreaksRepository;
import org.carly.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public BreaksRest updatePart(BreaksRest part) {
        return null;
    }

    @Override
    public void deletePart(ObjectId id) {

    }
}
