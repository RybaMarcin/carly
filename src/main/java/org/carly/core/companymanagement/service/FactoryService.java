package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.FactoryDetailsResponse;
import org.carly.core.companymanagement.mapper.FactoryDetailMapper;
import org.carly.core.companymanagement.repository.CompanyRepository;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.partsmanagement.service.BrakeFindService;
import org.carly.core.security.model.UserRole;
import org.carly.core.usermanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
                companyRepository.findByIdAndRolesIn(new ObjectId(factoryRequest.getFactoryId()), UserRole.CARLY_FACTORY.name()).stream().findFirst().orElse(null);
        Collection<?> parts = new ArrayList<>();
        if (factoryRequest.getPartType() == PartType.BRAKES) {
            parts = brakeFindService.findAllBreaksByFactoryId(factoryRequest.getFactoryId());
        }
        final FactoryDetailsResponse response = factoryDetailMapper.mapFromDomain(user);
//        response.setParts(parts);
        return null;
    }
}
