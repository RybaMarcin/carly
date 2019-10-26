package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.PaintingRest;
import org.carly.parts_management.core.model.Painting;
import org.carly.shared.utils.MapperService;
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
