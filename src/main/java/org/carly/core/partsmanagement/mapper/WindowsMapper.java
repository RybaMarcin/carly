package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.partsmanagement.WindowsRest;
import org.carly.core.partsmanagement.model.Windows;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class WindowsMapper implements MapperService<WindowsRest, Windows> {

    @Override
    public WindowsRest simplifyRestObject(Windows domain) {
        WindowsRest rest = new WindowsRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Windows simplifyDomainObject(WindowsRest rest) {
        Windows domain = new Windows();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public WindowsRest mapFromDomainObject(Windows domain, WindowsRest rest) {
        return null;
    }

    @Override
    public Windows mapToDomainObject(Windows domain, WindowsRest rest) {
        return null;
    }


}
