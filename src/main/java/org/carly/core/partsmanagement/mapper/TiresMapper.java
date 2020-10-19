package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.partsmanagement.TiresRest;
import org.carly.core.partsmanagement.model.Tires;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class TiresMapper implements MapperService<TiresRest, Tires> {

    @Override
    public TiresRest simplifyRestObject(Tires domain) {
        TiresRest rest = new TiresRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Tires simplifyDomainObject(TiresRest rest) {
        Tires domain = new Tires();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public TiresRest mapFromDomainObject(Tires domain, TiresRest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Tires mapToDomainObject(Tires domain, TiresRest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }

}
