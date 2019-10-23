package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.BrandRest;
import org.carly.shared.utils.MapperService;
import org.carly.parts_management.api.model.WheelsRest;
import org.carly.vehicle_management.core.model.Brand;
import org.carly.parts_management.core.model.Wheels;
import org.springframework.stereotype.Component;

@Component
public class WheelsMapper implements MapperService<WheelsRest, Wheels> {

    @Override
    public WheelsRest simplifyRestObject(Wheels domain) {
        WheelsRest rest = new WheelsRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Wheels simplifyDomainObject(WheelsRest rest) {
        Wheels domain = new Wheels();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public WheelsRest mapFromDomainObject(Wheels domain, WheelsRest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
        rest.setBrand(new BrandRest());
        rest.getBrand().setId(domain.getBrand().getId());
        rest.getBrand().setName(domain.getBrand().getName());
        rest.getBrand().setRating(domain.getBrand().getRating());
        rest.setDiameter(domain.getDiameter());
        rest.setPreview(domain.getPreview());
        return rest;
    }

    @Override
    public Wheels mapToDomainObject(Wheels domain, WheelsRest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
        domain.setBrand(new Brand());
        domain.getBrand().setId(rest.getBrand().getId());
        domain.getBrand().setName(rest.getBrand().getName());
        domain.getBrand().setRating(rest.getBrand().getRating());
        domain.setDiameter(rest.getDiameter());
        domain.setPreview(rest.getPreview());
        return domain;
    }


}
