package org.carly.core.partsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.BreaksRequest;
import org.carly.api.rest.response.BreaksResponse;
import org.carly.core.partsmanagement.mapper.BreaksRequestMapper;
import org.carly.core.partsmanagement.mapper.BreaksResponseMapper;
import org.carly.core.partsmanagement.model.entity.Breaks;
import org.carly.core.partsmanagement.repository.BreaksRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class BreaksSaveService {

    private final BreaksRequestMapper breaksRequestMapper;
    private final BreaksResponseMapper breaksResponseMapper;
    private final BreaksRepository breaksRepository;
    private final LoggedUserProvider loggedUserProvider;

    public BreaksSaveService(BreaksRequestMapper breaksRequestMapper,
                             BreaksResponseMapper breaksResponseMapper,
                             BreaksRepository breaksRepository,
                             LoggedUserProvider loggedUserProvider) {
        this.breaksRequestMapper = breaksRequestMapper;
        this.breaksResponseMapper = breaksResponseMapper;
        this.breaksRepository = breaksRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<BreaksResponse> createPart(BreaksRequest request) {
        Breaks breaks = breaksRequestMapper.simplifyDomainObject(request);
        breaks.setCreatedDate(LocalDateTime.now());
        breaks.setCreateBy(loggedUserProvider.provideUserDetail().getEmail());
        breaksRepository.save(breaks);
        BreaksResponse response = breaksResponseMapper.mapFromRestToResponse(request);
        log.info("Breaks with id: {} successfully created!", request.getId());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BreaksResponse> updatePart(BreaksRequest part) {
        Breaks breaksToUpdate = breaksRepository.findById(part.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Breaks updatedBreaks = breaksRequestMapper.mapToDomainObject(breaksToUpdate, part);
        breaksRepository.save(updatedBreaks);
        BreaksResponse response = breaksResponseMapper.mapFromRestToResponse(part);
        log.info("Breaks with id: {} successfully updated!", part.getId());
        return ResponseEntity.ok(response);
    }

    public void deletePart(ObjectId id) {
        Breaks breaks = breaksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        breaksRepository.delete(breaks);
        log.info("Breaks with id: {} successfully deleted!", id);
    }
}
