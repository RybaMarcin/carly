package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.EngineRest;
import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.core.model.Engine;
import org.springframework.stereotype.Component;

@Component
public class EngineMapper implements MapperService<EngineRest, Engine> {



    @Override
    public EngineRest mapFromDomainObject(Engine domain, EngineRest rest) {
        return null;
    }

    @Override
    public Engine mapToDomainObject(Engine domain, EngineRest rest) {
        return null;
    }

    @Override
    public EngineRest simplifyRestObject(Engine domain) {
        return null;
    }

    @Override
    public Engine simplifyDomainObject(EngineRest rest) {
        return null;
    }
}
