package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.core.partsmanagement.mapper.BrakeResponseMapper;
import org.carly.core.partsmanagement.repository.BrakeMongoRepository;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.carly.core.partsmanagement.repository.BrakeRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BrakeFindService  {

    private final BrakeResponseMapper brakeResponseMapper;
    private final BrakeRepository brakesRepository;
    private final BrakeMongoRepository brakeMongoRepository;

    public BrakeFindService(BrakeResponseMapper brakeResponseMapper,
                            BrakeRepository brakeRepository,
                            BrakeMongoRepository brakeMongoRepository) {
        this.brakeResponseMapper = brakeResponseMapper;
        this.brakesRepository = brakeRepository;
        this.brakeMongoRepository = brakeMongoRepository;
    }

    public Collection<BrakeResponse> findAll() {
        List<Brake> brakeList = brakesRepository.findAll();
        log.info("Breaks list contains: {}", brakeList.size());
        return brakeList.stream().map(brakeResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }

    public BrakeResponse findPartById(ObjectId id) {
        Brake aBrake = brakesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        log.info("Breaks with id: {} was found!", id);
        return brakeResponseMapper.simplifyRestObject(aBrake);
    }


    public Page<BrakeResponse> findBreaks(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return brakeMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(brakeResponseMapper::simplifyRestObject);
    }

    public Collection<BrakeResponse> findAllBreaksByFactoryId(String companyId) {
        Collection<Brake> brakes = brakesRepository.findAllByFactoryCarlyFactoryId(new ObjectId(companyId));
        log.info("Find: ({}) - brakes", brakes.size());
        return brakes.stream().map(brakeResponseMapper::simplifyRestObject).collect(Collectors.toList());
    }
}
