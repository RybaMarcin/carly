package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.ErrorResponse;
import org.carly.api.rest.response.FactoryDetailsResponse;
import org.carly.api.rest.response.factories.PartMinModel;
import org.carly.core.companymanagement.mapper.FactoryDetailMapper;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.carly.core.companymanagement.repository.CompanyRepository;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.partsmanagement.model.entity.Part;
import org.carly.core.partsmanagement.service.BrakeFindService;
import org.carly.core.partsmanagement.service.PartsFindService;
import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.security.model.UserRole;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.usermanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.carly.core.shared.exception.ErrorCode.ENTITY_NOT_FOUND;

@Slf4j
@Service
public class FactoryService {

    private final CompanyRepository companyRepository;
    private final CompanyMatchingService companyMatchingService;
    private final BrakeFindService brakeFindService;
    private final PartsFindService partsFindService;
    private final FactoryDetailMapper factoryDetailMapper;
    private final LoggedUserProvider loggedUserProvider;

    public FactoryService(CompanyRepository companyRepository,
                          CompanyMatchingService companyMatchingService,
                          BrakeFindService brakeFindService,
                          PartsFindService partsFindService,
                          FactoryDetailMapper factoryDetailMapper,
                          LoggedUserProvider loggedUserProvider) {
        this.companyRepository = companyRepository;
        this.companyMatchingService = companyMatchingService;
        this.brakeFindService = brakeFindService;
        this.partsFindService = partsFindService;
        this.factoryDetailMapper = factoryDetailMapper;
        this.loggedUserProvider = loggedUserProvider;
    }

    public ResponseEntity<?> getFactoryById(FactoryRequest factoryRequest) {
        final User user =
                companyRepository.findByIdAndRoles(new ObjectId(factoryRequest.getFactoryId()), new CarlyGrantedAuthority(UserRole.CARLY_FACTORY))
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND.getDescription()));
        Collection<? extends Part> parts = null;
        List<PartMinModel> responseParts;

        // todo: We should return here map with parts I guess
        if (factoryRequest.getPartType() == PartType.BRAKES) {
            parts = brakeFindService.findAllDomainBreaksByFactoryId(factoryRequest.getFactoryId());
        }
        if (parts != null) {
            responseParts = factoryDetailMapper.mapFromPartToMinModel(parts);
            final FactoryDetailsResponse response = factoryDetailMapper.mapFromDomain(user);
            CompanyMatch match = companyMatchingService.findMatchByCompanyIdAndFactoryId(loggedUserProvider.loggedUser().getId(),
                                                                                         new ObjectId(factoryRequest.getFactoryId()));
            if (match != null) {
                response.setMatchStatus(match.getStatus().name());
            }
            response.setParts(responseParts);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Cannot obtain factories parts!"));
    }

    public ResponseEntity<?> getPartDetailsById(String partId, PartType partType) {
        if (partType == PartType.BRAKES) {
            final BrakeResponse brakeResponse = brakeFindService.findPartById(new ObjectId(partId));
            return ResponseEntity.ok(brakeResponse);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Cannot obtain details for part!"));
    }
}
