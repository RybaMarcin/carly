package org.carly.core.partsmanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.response.BrakeResponse;
import org.carly.api.rest.response.FactoryResponse;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class BrakeResponseMapper implements MapperService<BrakeResponse, Brake> {

    @Override
    public BrakeResponse simplifyRestObject(Brake domain) {
        BrakeResponse rest = new BrakeResponse();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Brake simplifyDomainObject(BrakeResponse rest) {
        Brake domain = new Brake();
        return mapToDomainObject(domain, rest);
    }

    public BrakeResponse mapFromRestToResponse(BrakeRequest request) {
        BrakeResponse response = new BrakeResponse();
        if (request.getId() != null) {
            response.setId(request.getId().toHexString());
        }
        response.setBrakeType(request.getBrakeType());
        response.setName(request.getName());
        response.setPreview(request.getPreview());
        response.setPrice(request.getPrice());
        return response;
    }

    @Override
    public BrakeResponse mapFromDomainObject(Brake domain, BrakeResponse rest) {
        rest.setId(domain.getId().toHexString());
        rest.setBrakeType(domain.getBrakeType());
        rest.setBrand(new FactoryResponse(
                domain.getFactory().getCarlyFactoryId().toHexString(),
                domain.getFactory().getName(),
                domain.getFactory().getRating()
        ));
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Brake mapToDomainObject(Brake domain, BrakeResponse rest) {
        domain.setId(new ObjectId(rest.getId()));
        domain.setBrakeType(rest.getBrakeType());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }

}
