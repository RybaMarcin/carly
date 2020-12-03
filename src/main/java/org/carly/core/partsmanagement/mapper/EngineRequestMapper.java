package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.EngineRequest;
import org.carly.api.rest.request.BrandRequest;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Factory;
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
        rest.setBrand(new BrandRequest(
                domain.getFactory().getCarlyFactoryId(),
                domain.getFactory().getName(),
                domain.getFactory().getRating()
        ));
        rest.setHorsePower(domain.getHorsePower());
        rest.setCapacity(domain.getCapacity());
        rest.setCylinderType(domain.getCylinderType().toString());
        rest.setPreview(domain.getPreview());
        return rest;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineRequest rest) {
        if (rest.getId() != null) {
            domain.setId(rest.getId());
        }
        domain.setName(rest.getName());
        domain.setPrice(rest.getPrice());
        domain.setFactory(new Factory(
                rest.getBrand().getCarlyFactoryId(),
                rest.getBrand().getName(),
                rest.getBrand().getRating()
        ));
        domain.setHorsePower(rest.getHorsePower());
        domain.setCapacity(rest.getCapacity());
        domain.setCylinderType(CylinderType.valueOf(rest.getCylinderType()));
        domain.setPreview(rest.getPreview());
        return domain;
    }
}
