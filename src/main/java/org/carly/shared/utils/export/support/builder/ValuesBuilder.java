package org.carly.shared.utils.export.support.builder;


import org.carly.shared.utils.export.support.model.ExportHeader;

import java.util.List;
import java.util.Map;

public interface ValuesBuilder {
    ValuesBuilder withValues(List<Map<ExportHeader, Object>> values);

    ValuesBuilder withValues(Map<ExportHeader, Object> values);
}
