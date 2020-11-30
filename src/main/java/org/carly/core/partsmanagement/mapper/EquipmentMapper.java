package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.EquipmentRequest;
import org.carly.api.rest.request.BrandRequest;
import org.carly.api.rest.response.EquipmentResponse;
import org.carly.api.rest.response.FactoryResponse;
import org.carly.core.partsmanagement.model.entity.Equipment;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Factory;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper implements MapperService<EquipmentRequest, Equipment> {

    @Override
    public Equipment simplifyDomainObject(EquipmentRequest request) {
        Equipment domain = new Equipment();
        return mapToDomainObject(domain, request);
    }

    @Override
    public EquipmentRequest simplifyRestObject(Equipment domain) {
        EquipmentRequest request = new EquipmentRequest();
        return mapFromDomainObject(domain, request);
    }

    @Override
    public EquipmentRequest mapFromDomainObject(Equipment domain, EquipmentRequest request) {
        request.setId(domain.getId());
        request.setBrandRequest(new BrandRequest(
                domain.getFactory().getCarlyFactoryId(),
                domain.getFactory().getName(),
                domain.getFactory().getRating()
        ));
        request.setName(domain.getName());
        request.setPrice(domain.getPrice());
        request.setEquipmentType(domain.getType());
        return request;
    }

    @Override
    public Equipment mapToDomainObject(Equipment domain, EquipmentRequest request) {
        if (request.getId() != null) {
            domain.setId(request.getId());
        }
        domain.setName(request.getName());
        domain.setFactory(new Factory(
                request.getBrandRequest().getCarlyFactoryId(),
                request.getBrandRequest().getName(),
                request.getBrandRequest().getRating()
        ));
        domain.setPrice(request.getPrice());
        domain.setType(request.getEquipmentType());
        return domain;
    }

    public EquipmentResponse mapFromDomainToResponse(Equipment equipment) {
        EquipmentResponse response = new EquipmentResponse();
        response.setId(equipment.getId().toHexString());
        response.setName(equipment.getName());
        response.setPrice(equipment.getPrice());
        response.setFactoryResponse(new FactoryResponse(
                equipment.getFactory().getCarlyFactoryId().toHexString(),
                equipment.getFactory().getName(),
                equipment.getFactory().getRating()
        ));
        return response;
    }
}
