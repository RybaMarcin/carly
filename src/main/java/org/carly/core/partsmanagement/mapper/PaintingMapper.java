package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.PaintingRequest;
import org.carly.api.rest.response.PaintingResponse;
import org.carly.core.partsmanagement.model.dictionaries.PaintType;
import org.carly.core.partsmanagement.model.entity.Painting;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class PaintingMapper implements MapperService<PaintingRequest, Painting> {

    @Override
    public PaintingRequest simplifyRestObject(Painting domain) {
        PaintingRequest request = new PaintingRequest();
        return mapFromDomainObject(domain, request);
    }

    @Override
    public Painting simplifyDomainObject(PaintingRequest request) {
        Painting domain = new Painting();
        return mapToDomainObject(domain, request);
    }

    @Override
    public PaintingRequest mapFromDomainObject(Painting domain, PaintingRequest request) {
        request.setId(domain.getId());
        request.setName(domain.getName());
        request.setType(domain.getType().getType());
        return request;
    }

    @Override
    public Painting mapToDomainObject(Painting domain, PaintingRequest request) {
        if (request.getId() != null) {
            domain.setId(domain.getId());
        }
        domain.setName(request.getName());
        domain.setType(PaintType.of(request.getType()));
        return domain;
    }

    public PaintingResponse mapFromDomainToResponse(Painting domain) {
        PaintingResponse response = new PaintingResponse();
        response.setId(domain.getId().toHexString());
        response.setName(domain.getName());
        response.setType(domain.getType().getType());
        return response;
    }

}
