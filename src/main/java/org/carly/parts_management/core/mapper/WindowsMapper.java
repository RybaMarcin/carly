package org.carly.parts_management.core.mapper;

import org.carly.parts_management.api.model.WindowsRest;
import org.carly.parts_management.core.model.Windows;
import org.carly.shared.utils.MapperService;
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
