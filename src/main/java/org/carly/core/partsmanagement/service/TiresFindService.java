package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.TiresSearchCriteriaRequest;
import org.carly.api.rest.response.TiresResponse;
import org.carly.core.partsmanagement.mapper.TiresMapper;
import org.carly.core.partsmanagement.model.entity.Tires;
import org.carly.core.partsmanagement.repository.TiresMongoRepository;
import org.carly.core.partsmanagement.repository.TiresRepository;
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
public class TiresFindService   {

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

    public Collection<TiresResponse> findAll() {
        List<Tires> tiresList = tiresRepository.findAll();
        log.info("Tires list contains: {}", tiresList.size());
        return tiresList.stream().map(tiresMapper::mapFromDomainToResponse).collect(Collectors.toList());
    }

    public ResponseEntity<TiresResponse> findPartById(ObjectId id) {
        Tires tires = tiresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Tires with id: {} was found!", id);
        return ResponseEntity.ok(tiresMapper.mapFromDomainToResponse(tires));
    }

    public Page<TiresResponse> findTires(TiresSearchCriteriaRequest searchCriteria,
                                         Pageable pageable) {
        return tiresMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(tiresMapper::mapFromDomainToResponse);
    }
}
