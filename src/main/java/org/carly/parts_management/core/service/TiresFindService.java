package org.carly.parts_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.parts_management.api.model.TiresRest;
import org.carly.parts_management.api.model.criteria.TiresSearchCriteriaRest;
import org.carly.parts_management.core.mapper.TiresMapper;
import org.carly.parts_management.core.model.Tires;
import org.carly.parts_management.core.repository.TiresMongoRepository;
import org.carly.parts_management.core.repository.TiresRepository;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.part_services.PartFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class TiresFindService implements PartFindService {


    private final TiresMapper tiresMapper;
    private final TiresRepository tiresRepository;
    private final TiresMongoRepository tiresMongoRepository;


    public TiresFindService(TiresMapper tiresMapper,
                            TiresRepository tiresRepository,
                            TiresMongoRepository tiresMongoRepository) {
        this.tiresMapper = tiresMapper;
        this.tiresRepository = tiresRepository;
        this.tiresMongoRepository = tiresMongoRepository;
    }

    @Override
    public Collection<TiresRest> findAll() {
        List<Tires> tiresList = tiresRepository.findAll();
        log.info("Tires list contains: {}", tiresList.size());
        return tiresList.stream().map(tiresMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public TiresRest findPartById(ObjectId id) {
        Tires tires = tiresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Tires with id: {} was found!", id);
        return tiresMapper.simplifyRestObject(tires);

    }

    public Page<TiresRest> findTires(TiresSearchCriteriaRest searchCriteria,
                                     Pageable pageable) {
        return tiresMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(tiresMapper::simplifyRestObject);
    }

}
