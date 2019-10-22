package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.BrandRest;
import org.carly.parts_management.api.model.EngineRest;
import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.core.model.Brand;
import org.carly.parts_management.core.model.Engine;
import org.springframework.stereotype.Component;

@Component
public class EngineMapper implements MapperService<EngineRest, Engine> {


    @Override
    public EngineRest simplifyRestObject(Engine domain) {
        EngineRest rest = new EngineRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Engine simplifyDomainObject(EngineRest rest) {
        Engine domain = new Engine();
        return mapToDomainObject(domain, rest);
    }


    @Override
    public EngineRest mapFromDomainObject(Engine domain, EngineRest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
        rest.setPrice(domain.getPrice());
        rest.setBrand(new BrandRest());
        rest.getBrand().setId(domain.getBrand().getId());
        rest.getBrand().setName(domain.getBrand().getName());
        rest.getBrand().setRating(domain.getBrand().getRating());
        rest.setHorsePower(domain.getHorsePower());
        rest.setWeight(domain.getWeight());
        rest.setCapacity(domain.getCapacity());
        rest.setNumberOfCylinders(domain.getNumberOfCylinders());
        return rest;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineRest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
        domain.setPrice(rest.getPrice());
        domain.setBrand(new Brand());
        domain.getBrand().setId(rest.getBrand().getId());
        domain.getBrand().setName(rest.getBrand().getName());
        domain.getBrand().setRating(rest.getBrand().getRating());
        domain.setHorsePower(rest.getHorsePower());
        domain.setWeight(rest.getWeight());
        domain.setCapacity(rest.getCapacity());
        domain.setNumberOfCylinders(rest.getNumberOfCylinders());
        return domain;
    }


}
