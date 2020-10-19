package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.partsmanagement.PaintingRest;
import org.carly.core.partsmanagement.model.Painting;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class PaintingMapper implements MapperService<PaintingRest, Painting> {

    @Override
    public PaintingRest simplifyRestObject(Painting domain) {
        PaintingRest rest = new PaintingRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Painting simplifyDomainObject(PaintingRest rest) {
        Painting domain = new Painting();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public PaintingRest mapFromDomainObject(Painting domain, PaintingRest rest) {
        return null;
    }

    @Override
    public Painting mapToDomainObject(Painting domain, PaintingRest rest) {
        return null;
    }


}
