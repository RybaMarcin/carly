package org.carly.core.partsmanagement.mapper;

import org.carly.core.shared.utils.MapperService;
import org.carly.api.rest.request.WheelsRequest;
import org.carly.core.partsmanagement.model.Wheels;
import org.springframework.stereotype.Component;

@Component
public class WheelsRequestMapper implements MapperService<WheelsRequest, Wheels> {

    @Override
    public WheelsRequest simplifyRestObject(Wheels domain) {
        WheelsRequest rest = new WheelsRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Wheels simplifyDomainObject(WheelsRequest rest) {
        Wheels domain = new Wheels();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public WheelsRequest mapFromDomainObject(Wheels domain, WheelsRequest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
//        rest.setBrand(new BrandRest());
//        rest.getBrand().setId(domain.getBrand().getId());
//        rest.getBrand().setName(domain.getBrand().getName());
//        rest.getBrand().setRating(domain.getBrand().getRating());
        rest.setDiameter(domain.getDiameter());
        rest.setPreview(domain.getPreview());
        return rest;
    }

    @Override
    public Wheels mapToDomainObject(Wheels domain, WheelsRequest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
//        domain.setBrand(new Brand());
//        domain.getBrand().setId(rest.getBrand().getId());
//        domain.getBrand().setName(rest.getBrand().getName());
//        domain.getBrand().setRating(rest.getBrand().getRating());
        domain.setDiameter(rest.getDiameter());
        domain.setPreview(rest.getPreview());
        return domain;
    }
}
