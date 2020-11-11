package org.carly.core.shared.utils.export.support.formatter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExportFormatter {

    private static final Map<Class, Formatter> FORMATTERS = new HashMap<>();
    private static final DefaultFormatter DEFAULT_CSV_FORMATTER = new DefaultFormatter();

    static {
        FORMATTERS.put(LocalDate.class, new LocalDateFormatter());
        FORMATTERS.put(LocalDateTime.class, new LocalDateTimeFormatter());
        FORMATTERS.put(BigDecimal.class, new BigDecimalFormatter());
    }

    private ExportFormatter() {
    }

    public static String format(Object value) {
        return FORMATTERS.getOrDefault(value.getClass(), DEFAULT_CSV_FORMATTER).format(value);
    }
}
