package org.carly.parts_management.core.mapper;

import org.carly.shared.utils.MapperService;
import org.carly.parts_management.api.model.WheelsRest;
import org.carly.vehicle_management.core.model.Wheels;
import org.springframework.stereotype.Component;

@Component
public class WheelsMapper implements MapperService<WheelsRest, Wheels> {


    @Override
    public WheelsRest mapFromDomainObject(Wheels domain, WheelsRest rest) {
        return null;
    }

    @Override
    public Wheels mapToDomainObject(Wheels domain, WheelsRest rest) {
        return null;
    }

    @Override
    public WheelsRest simplifyRestObject(Wheels domain) {
        return null;
    }

    @Override
    public Wheels simplifyDomainObject(WheelsRest rest) {
        return null;
    }
}
