package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.TiresRequest;
import org.carly.core.partsmanagement.model.entity.Tires;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class TiresMapper implements MapperService<TiresRequest, Tires> {

    @Override
    public TiresRequest simplifyRestObject(Tires domain) {
        TiresRequest rest = new TiresRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Tires simplifyDomainObject(TiresRequest rest) {
        Tires domain = new Tires();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public TiresRequest mapFromDomainObject(Tires domain, TiresRequest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Tires mapToDomainObject(Tires domain, TiresRequest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }

}
