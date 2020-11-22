package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.BreaksRequest;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.core.partsmanagement.model.entity.Breaks;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Factory;
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
        rest.setBreaksType(domain.getBreaksType());
        rest.setName(domain.getName());
        rest.setPreview(domain.getPreview());
        rest.setPrice(domain.getPrice());
        rest.setFactoryRequest(
                new FactoryRequest(
                        domain.getFactory().getCarlyFactoryId(),
                        domain.getFactory().getName(),
                        domain.getFactory().getRating()
                )
        );
        return rest;
    }

    @Override
    public Breaks mapToDomainObject(Breaks domain, BreaksRequest rest) {
        if (rest.getId() != null) {
            domain.setId(rest.getId());
        }
        domain.setBreaksType(rest.getBreaksType());
        domain.setName(rest.getName());
        domain.setPreview(rest.getPreview());
        domain.setPrice(rest.getPrice());
        domain.setFactory(new Factory(
                rest.getFactoryRequest().getCarlyFactoryId(),
                rest.getFactoryRequest().getName(),
                rest.getFactoryRequest().getRating())
        );
        return domain;
    }
}
