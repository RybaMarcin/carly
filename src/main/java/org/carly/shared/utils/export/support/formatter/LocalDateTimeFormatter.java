package org.carly.shared.utils.export.support.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public String format(final LocalDateTime value) {
        return value != null ? FORMATTER.format(value) : "";
    }
}
