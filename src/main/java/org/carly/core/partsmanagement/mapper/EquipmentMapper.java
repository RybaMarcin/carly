package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.EquipmentRest;
import org.carly.core.partsmanagement.model.entity.Equipment;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper implements MapperService<EquipmentRest, Equipment> {


    @Override
    public Equipment simplifyDomainObject(EquipmentRest rest) {
        Equipment domain = new Equipment();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public EquipmentRest simplifyRestObject(Equipment domain) {
        EquipmentRest rest = new EquipmentRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public EquipmentRest mapFromDomainObject(Equipment domain, EquipmentRest rest) {
        return null;
    }

    @Override
    public Equipment mapToDomainObject(Equipment domain, EquipmentRest rest) {
        return null;
    }


}
