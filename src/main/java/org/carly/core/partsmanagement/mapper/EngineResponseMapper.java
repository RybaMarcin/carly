package org.carly.core.partsmanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.FactoryResponse;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.partsmanagement.model.dictionaries.CylinderType;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Factory;
import org.springframework.stereotype.Component;

@Component
public class EngineResponseMapper implements MapperService<EngineResponse, Engine> {

    @Override
    public EngineResponse simplifyRestObject(Engine domain) {
        EngineResponse response = new EngineResponse();
        return mapFromDomainObject(domain, response);
    }

    @Override
    public Engine simplifyDomainObject(EngineResponse response) {
        Engine domain = new Engine();
        return mapToDomainObject(domain, response);
    }

    @Override
    public EngineResponse mapFromDomainObject(Engine domain, EngineResponse response) {
        response.setId(domain.getId().toHexString());
        response.setName(domain.getName());
        response.setPrice(domain.getPrice());
        response.setBrand(new FactoryResponse());
        response.getBrand().setCarlyFactoryId(domain.getFactory().getCarlyFactoryId().toHexString());
        response.getBrand().setName(domain.getFactory().getName());
        response.setHorsePower(domain.getHorsePower());
        response.setCapacity(domain.getCapacity());
        response.setCylinderType(domain.getCylinderType().toString());
        response.setPreview(domain.getPreview());
        return response;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineResponse response) {
        domain.setId(new ObjectId(response.getId()));
        domain.setName(response.getName());
        domain.setPrice(response.getPrice());
        domain.setFactory(new Factory());
        domain.getFactory().setCarlyFactoryId(new ObjectId(response.getBrand().getCarlyFactoryId()));
        domain.getFactory().setName(response.getBrand().getName());
        domain.setHorsePower(response.getHorsePower());
        domain.setCapacity(response.getCapacity());
        domain.setCylinderType(CylinderType.valueOf(response.getCylinderType()));
        domain.setPreview(response.getPreview());
        return domain;
    }
}
