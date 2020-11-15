package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.EngineRequest;
import org.carly.api.rest.request.BrandRequest;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class EngineRequestMapper implements MapperService<EngineRequest, Engine> {

    @Override
    public EngineRequest simplifyRestObject(Engine domain) {
        EngineRequest rest = new EngineRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Engine simplifyDomainObject(EngineRequest rest) {
        Engine domain = new Engine();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public EngineRequest mapFromDomainObject(Engine domain, EngineRequest rest) {
        rest.setId(domain.getId());
        rest.setName(domain.getName());
        rest.setPrice(domain.getPrice());
        rest.setBrand(new BrandRequest());
        rest.getBrand().setId(domain.getBrand().getId());
        rest.getBrand().setName(domain.getBrand().getName());
        rest.setHorsePower(domain.getHorsePower());
        rest.setWeight(domain.getWeight());
        rest.setCapacity(domain.getCapacity());
        rest.setCylinderType(domain.getCylinderType().toString());
        return rest;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineRequest rest) {
        domain.setId(rest.getId());
        domain.setName(rest.getName());
        domain.setPrice(rest.getPrice());
        domain.setBrand(new Brand());
        domain.getBrand().setId(rest.getBrand().getId());
        domain.getBrand().setName(rest.getBrand().getName());
        domain.setHorsePower(rest.getHorsePower());
        domain.setWeight(rest.getWeight());
        domain.setCapacity(rest.getCapacity());
        domain.setCylinderType(CylinderType.valueOf(rest.getCylinderType()));
        return domain;
    }
}