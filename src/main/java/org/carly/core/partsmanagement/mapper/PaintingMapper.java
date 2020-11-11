package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.PaintingRequest;
import org.carly.core.partsmanagement.model.Painting;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class PaintingMapper implements MapperService<PaintingRequest, Painting> {

    @Override
    public PaintingRequest simplifyRestObject(Painting domain) {
        PaintingRequest rest = new PaintingRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Painting simplifyDomainObject(PaintingRequest rest) {
        Painting domain = new Painting();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public PaintingRequest mapFromDomainObject(Painting domain, PaintingRequest rest) {
        return null;
    }

    @Override
    public Painting mapToDomainObject(Painting domain, PaintingRequest rest) {
        return null;
    }


}
