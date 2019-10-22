package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.WheelsRest;
import org.carly.parts_management.core.mapper.WheelsMapper;
import org.carly.parts_management.core.repository.WheelsRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartSaveService;
import org.carly.parts_management.core.model.Wheels;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

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


    @Override
    public WheelsRest createPart(WheelsRest part) {
        Wheels wheels = wheelsMapper.simplifyDomainObject(part);
        wheelsRepository.save(wheels);
        log.info("Wheels with id: {} successfully created!", part.getId());
        return part;
    }

    @Override
    public WheelsRest updatePart(WheelsRest part) {
        Wheels wheelsToUpdate = wheelsRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Wheels updatedWheels = wheelsMapper.mapToDomainObject(wheelsToUpdate, part);
        wheelsRepository.save(updatedWheels);
        log.info("Wheels with id: {} successfully updated!", part.getId());
        return part;
    }

    @Override
    public void deletePart(ObjectId id) {
        Wheels wheels = wheelsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        wheelsRepository.delete(wheels);
        log.info("Wheels with id: {} successfully deleted!", id);
    }
}
