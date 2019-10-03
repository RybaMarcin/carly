package org.carly.user_management.utils.export.support.builder;


import org.carly.user_management.utils.export.support.model.ExportHeader;

public interface HeadersBuilder {
    ValuesBuilder withHeader(ExportHeader... headers);
}
