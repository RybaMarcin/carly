package org.carly.core.shared.utils.export.support.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public String format(final LocalDate value) {
        return value != null ? FORMATTER.format(value) : "";
    }
}
