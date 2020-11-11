package org.carly.core.partsmanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.BreaksRequest;
import org.carly.api.rest.response.BreaksResponse;
import org.carly.core.partsmanagement.model.Breaks;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class BreaksResponseMapper implements MapperService<BreaksResponse, Breaks> {

    @Override
    public BreaksResponse simplifyRestObject(Breaks domain) {
        BreaksResponse rest = new BreaksResponse();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Breaks simplifyDomainObject(BreaksResponse rest) {
        Breaks domain = new Breaks();
        return mapToDomainObject(domain, rest);
    }

    public BreaksResponse mapFromRestToResponse(BreaksRequest request) {
        BreaksResponse response = new BreaksResponse();
        response.setId(request.getId().toHexString());
//        rest.setBreaksType(domain.getBreaksType());
        response.setName(request.getName());
        response.setPreview(request.getPreview());
        response.setPrice(request.getPrice());
        return response;
    }

    @Override
    public BreaksResponse mapFromDomainObject(Breaks domain, BreaksResponse rest) {
        rest.setId(domain.getId().toHexString());
//        rest.setBreaksType(domain.getBreaksType());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Breaks mapToDomainObject(Breaks domain, BreaksResponse rest) {
        domain.setId(new ObjectId(rest.getId()));
//        domain.setBreaksType(rest.getBreaksType());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }

}
