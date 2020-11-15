package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.response.WheelsResponse;
import org.carly.core.partsmanagement.model.dictionaries.DiameterType;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class WheelsResponseMapper implements MapperService<WheelsResponse,  Wheels> {
    @Override
    public WheelsResponse simplifyRestObject(Wheels domain) {
        WheelsResponse response = new WheelsResponse();
        return mapFromDomainObject(domain, response);
    }

    @Override
    public Wheels simplifyDomainObject(WheelsResponse response) {
        Wheels domain = new Wheels();
        return mapToDomainObject(domain, response);
    }

    @Override
    public WheelsResponse mapFromDomainObject(Wheels domain, WheelsResponse response) {
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDiameterType(domain.getDiameterType().toString());
        response.setPreview(domain.getPreview());
        return response;
    }

    @Override
    public Wheels mapToDomainObject(Wheels domain, WheelsResponse response) {
        domain.setId(response.getId());
        domain.setName(response.getName());
        domain.setDiameterType(DiameterType.valueOf(response.getDiameterType()));
        domain.setPreview(response.getPreview());
        return domain;
    }
}