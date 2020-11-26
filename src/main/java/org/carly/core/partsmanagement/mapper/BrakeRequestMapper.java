package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.BrakeRequest;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Factory;
import org.springframework.stereotype.Component;

@Component
public class BrakeRequestMapper implements MapperService<BrakeRequest, Brake> {

    @Override
    public BrakeRequest simplifyRestObject(Brake domain) {
        BrakeRequest rest = new BrakeRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Brake simplifyDomainObject(BrakeRequest rest) {
        Brake domain = new Brake();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public BrakeRequest mapFromDomainObject(Brake domain, BrakeRequest rest) {
        rest.setId(domain.getId());
        rest.setBrakeType(domain.getBrakeType());
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
    public Brake mapToDomainObject(Brake domain, BrakeRequest rest) {
        if (rest.getId() != null) {
            domain.setId(rest.getId());
        }
        domain.setBrakeType(rest.getBrakeType());
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
