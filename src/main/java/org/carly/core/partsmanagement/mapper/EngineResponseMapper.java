package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.BrandRequest;
import org.carly.api.rest.response.BrandResponse;
import org.carly.api.rest.response.EngineResponse;
import org.carly.core.partsmanagement.model.Engine;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Brand;
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
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setPrice(domain.getPrice());
        response.setBrand(new BrandResponse());
        response.getBrand().setId(domain.getBrand().getId());
        response.getBrand().setName(domain.getBrand().getName());
        response.setHorsePower(domain.getHorsePower());
        response.setWeight(domain.getWeight());
        response.setCapacity(domain.getCapacity());
        response.setNumberOfCylinders(domain.getNumberOfCylinders());
        return response;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineResponse response) {
        domain.setId(response.getId());
        domain.setName(response.getName());
        domain.setPrice(response.getPrice());
        domain.setBrand(new Brand());
        domain.getBrand().setId(response.getBrand().getId());
        domain.getBrand().setName(response.getBrand().getName());
        domain.setHorsePower(response.getHorsePower());
        domain.setWeight(response.getWeight());
        domain.setCapacity(response.getCapacity());
        domain.setNumberOfCylinders(response.getNumberOfCylinders());
        return domain;
    }
}