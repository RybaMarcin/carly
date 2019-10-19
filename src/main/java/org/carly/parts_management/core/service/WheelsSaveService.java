package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.WheelsRest;
import org.carly.parts_management.core.mapper.WheelsMapper;
import org.carly.parts_management.core.repository.WheelsRepository;
import org.carly.shared.service.part_services.PartSaveService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WheelsSaveService implements PartSaveService<WheelsRest> {

    private final WheelsMapper wheelsMapper;
    private final WheelsRepository wheelsRepository;


    public WheelsSaveService(WheelsMapper wheelsMapper,
                             WheelsRepository wheelsRepository) {
        this.wheelsMapper = wheelsMapper;
        this.wheelsRepository = wheelsRepository;
    }


    //todo: Finish implementation

    @Override
    public WheelsRest createPart(WheelsRest part) {
        return null;
    }

    @Override
    public WheelsRest updatePart(WheelsRest part) {
        return null;
    }

    @Override
    public void deletePart(ObjectId id) {

    }
}
