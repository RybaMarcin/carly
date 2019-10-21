package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.BreaksRest;
import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.core.model.Breaks;
import org.springframework.stereotype.Component;

@Component
public class BreaksMapper implements MapperService<BreaksRest, Breaks> {


    @Override
    public BreaksRest simplifyRestObject(Breaks domain) {
        return null;
    }

    @Override
    public Breaks simplifyDomainObject(BreaksRest rest) {
        return null;
    }

    @Override
    public BreaksRest mapFromDomainObject(Breaks domain, BreaksRest rest) {
        return null;
    }

    @Override
    public Breaks mapToDomainObject(Breaks domain, BreaksRest rest) {
        return null;
    }

}
