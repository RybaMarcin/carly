package org.carly.shared.utils.export.support.builder;


import org.carly.shared.utils.export.support.model.ExportHeader;

public interface HeadersBuilder {
    ValuesBuilder withHeader(ExportHeader... headers);
}
