package org.carly.user_management.utils.export.support.formatter;

public class DefaultFormatter implements Formatter<Object> {

    @Override
    public String format(final Object value) {
        return value != null ? value.toString() : "";
    }
}
