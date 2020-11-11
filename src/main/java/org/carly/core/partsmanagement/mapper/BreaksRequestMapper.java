package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.BreaksRequest;
import org.carly.core.partsmanagement.model.Breaks;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class BreaksRequestMapper implements MapperService<BreaksRequest, Breaks> {

    @Override
    public BreaksRequest simplifyRestObject(Breaks domain) {
        BreaksRequest rest = new BreaksRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Breaks simplifyDomainObject(BreaksRequest rest) {
        Breaks domain = new Breaks();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public BreaksRequest mapFromDomainObject(Breaks domain, BreaksRequest rest) {
        rest.setId(domain.getId());
//        rest.setBreaksType(domain.getBreaksType());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        return rest;
    }

    @Override
    public Breaks mapToDomainObject(Breaks domain, BreaksRequest rest) {
        domain.setId(rest.getId());
//        domain.setBreaksType(rest.getBreaksType());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        return domain;
    }
}
