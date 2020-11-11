package org.carly.core.shared.utils.export.support.builder;


import org.carly.core.shared.utils.export.support.model.ExportHeader;

public interface HeadersBuilder {
    ValuesBuilder withHeader(ExportHeader... headers);
}
