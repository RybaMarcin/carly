package org.carly.core.shared.utils.export.support.formatter;

public class DefaultFormatter implements Formatter<Object> {

    @Override
    public String format(final Object value) {
        return value != null ? value.toString() : "";
    }
}
