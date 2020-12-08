package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.ErrorResponse;
import org.carly.api.rest.response.FactoryDetailsResponse;
import org.carly.api.rest.response.factories.PartFactoriesMinModel;
import org.carly.core.companymanagement.mapper.FactoryDetailMapper;
import org.carly.core.companymanagement.repository.CompanyRepository;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.partsmanagement.model.entity.Part;
import org.carly.core.partsmanagement.service.BrakeFindService;
import org.carly.core.security.model.UserRole;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.usermanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.carly.core.shared.exception.ErrorCode.ENTITY_NOT_FOUND;

@Slf4j
@Service
public class FactoryService {

    private final CompanyRepository companyRepository;
    private final BrakeFindService brakeFindService;
    private final FactoryDetailMapper factoryDetailMapper;

    public FactoryService(CompanyRepository companyRepository,
                          BrakeFindService brakeFindService,
                          FactoryDetailMapper factoryDetailMapper) {
        this.companyRepository = companyRepository;
        this.brakeFindService = brakeFindService;
        this.factoryDetailMapper = factoryDetailMapper;
    }

    public ResponseEntity<?> getFactoryById(FactoryRequest factoryRequest) {
        final User user =
                companyRepository.findByIdAndRolesIn(new ObjectId(factoryRequest.getFactoryId()), UserRole.CARLY_FACTORY.name())
                        .stream().
                        findFirst()
                        .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND.getDescription()));
        Collection<? extends Part> parts = null;
        List<PartFactoriesMinModel> responseParts;

        if (factoryRequest.getPartType() == PartType.BRAKES) {
            parts = brakeFindService.findAllDomainBreaksByFactoryId(factoryRequest.getFactoryId());
        }
        if (parts != null) {
            responseParts = factoryDetailMapper.mapFromPartToMinModel(parts);
            final FactoryDetailsResponse response = factoryDetailMapper.mapFromDomain(user);
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
