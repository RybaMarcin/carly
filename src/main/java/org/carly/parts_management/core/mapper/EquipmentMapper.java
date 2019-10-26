package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.EquipmentRest;
import org.carly.parts_management.core.model.Equipment;
import org.carly.shared.utils.MapperService;
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
