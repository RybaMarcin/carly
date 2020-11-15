package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.request.WindowsRequest;
import org.carly.core.partsmanagement.model.entity.Windows;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class WindowsRequestMapper implements MapperService<WindowsRequest, Windows> {

    @Override
    public WindowsRequest simplifyRestObject(Windows domain) {
        WindowsRequest rest = new WindowsRequest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public Windows simplifyDomainObject(WindowsRequest rest) {
        Windows domain = new Windows();
        return mapToDomainObject(domain, rest);
    }

    @Override
    public WindowsRequest mapFromDomainObject(Windows domain, WindowsRequest rest) {
        return null;
    }

    @Override
    public Windows mapToDomainObject(Windows domain, WindowsRequest rest) {
        return null;
    }

}
