package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.response.WindowsResponse;
import org.carly.core.partsmanagement.model.entity.Windows;
import org.carly.core.shared.utils.MapperService;
import org.springframework.stereotype.Component;

@Component
public class WindowsResponseMapper implements MapperService<WindowsResponse, Windows> {

    @Override
    public WindowsResponse simplifyRestObject(Windows domain) {
        WindowsResponse response = new WindowsResponse();
        return mapFromDomainObject(domain, response);
    }

    @Override
    public Windows simplifyDomainObject(WindowsResponse response) {
        Windows domain = new Windows();
        return mapToDomainObject(domain, response);
    }

    @Override
    public WindowsResponse mapFromDomainObject(Windows domain, WindowsResponse response) {
        return null;
    }

    @Override
    public Windows mapToDomainObject(Windows domain, WindowsResponse response) {
        return null;
    }
}
