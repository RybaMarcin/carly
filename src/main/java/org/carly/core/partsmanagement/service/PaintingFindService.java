package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.partsmanagement.PaintingRest;
import org.carly.api.rest.partsmanagement.criteria.PaintingSearchCriteriaRest;
import org.carly.core.partsmanagement.mapper.PaintingMapper;
import org.carly.core.partsmanagement.model.Painting;
import org.carly.core.partsmanagement.repository.PaintingMongoRepository;
import org.carly.core.partsmanagement.repository.PaintingRepository;
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
public class PaintingFindService implements PartFindService {

    private final PaintingMapper paintingMapper;
    private final PaintingRepository paintingRepository;
    private final PaintingMongoRepository paintingMongoRepository;


    public PaintingFindService(PaintingMapper paintingMapper,
                               PaintingRepository paintingRepository,
                               PaintingMongoRepository paintingMongoRepository) {
        this.paintingMapper = paintingMapper;
        this.paintingRepository = paintingRepository;
        this.paintingMongoRepository = paintingMongoRepository;
    }

    @Override
    public Collection<PaintingRest> findAll() {
        List<Painting> paintingList = paintingRepository.findAll();
        log.info("Painting list contains: {}", paintingList.size());
        return paintingList.stream().map(paintingMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public PaintingRest findPartById(ObjectId id) {
        Painting painting = paintingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Painting with id: {} was found!", id);
        return paintingMapper.simplifyRestObject(painting);
    }

    public Page<PaintingRest> findPaintings(PaintingSearchCriteriaRest searchCriteria, Pageable pageable) {
        return paintingMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(paintingMapper::simplifyRestObject);
    }

}
