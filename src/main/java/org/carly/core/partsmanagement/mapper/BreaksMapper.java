package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.partsmanagement.BreaksRest;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.partsmanagement.model.Breaks;
import org.springframework.stereotype.Component;

@Component
public class BreaksMapper implements MapperService<BreaksRest, Breaks> {


    //todo: Add missing fields to mapper when models will be ready.

    @Override
    public BreaksRest simplifyRestObject(Breaks domain) {
        BreaksRest rest = new BreaksRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Breaks simplifyDomainObject(BreaksRest rest) {
        Breaks domain = new Breaks();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public BreaksRest mapFromDomainObject(Breaks domain, BreaksRest rest) {
        rest.setId(domain.getId());
//        rest.setBreaksType(domain.getBreaksType());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Breaks mapToDomainObject(Breaks domain, BreaksRest rest) {
        domain.setId(rest.getId());
//        domain.setBreaksType(rest.getBreaksType());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }

}
