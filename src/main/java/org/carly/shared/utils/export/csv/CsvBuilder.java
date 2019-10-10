package org.carly.shared.utils.export.csv;

import org.carly.shared.utils.export.support.builder.ExportBuilder;
import org.carly.shared.utils.export.support.builder.HeadersBuilder;
import org.carly.shared.utils.export.support.builder.ValuesBuilder;
import org.carly.shared.utils.export.support.model.ExportHeader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.carly.shared.utils.export.support.formatter.ExportFormatter.format;
import static org.springframework.util.CollectionUtils.isEmpty;

public class CsvBuilder implements ExportBuilder {

    private static final String SEPARATOR = "|";
    private static final String NEW_LINE = "\n";

    private final PrintWriter printWriter;
    private List<ExportHeader> HEADERS = new ArrayList<>();

    private CsvBuilder(PrintWriter printWriter) {
        this.printWriter = printWriter;
        this.printWriter.append("sep=" + SEPARATOR + NEW_LINE);
    }

    public static HeadersBuilder aCsv(PrintWriter printWriter) {
        return new CsvBuilder(printWriter);
    }

    @Override
    public ValuesBuilder withHeader(ExportHeader... headers) {
        HEADERS.addAll(Arrays.asList(headers));
        List<Object> headersToPrint = Arrays.stream(headers).map(ExportHeader::getLabel).collect(Collectors.toList());
        write(headersToPrint);
        return this;
    }

    @Override
    public ValuesBuilder withValues(List<Map<ExportHeader, Object>> values) {
        values.forEach(this::withValues);
        return this;
    }

    @Override
    public ValuesBuilder withValues(Map<ExportHeader, Object> values) {
        List<Object> valuesToPrint = new ArrayList<>();
        HEADERS.forEach(header -> valuesToPrint.add(values.get(header)));
        write(valuesToPrint);
        return this;
    }

    private void write(List<Object> values) {
        if (isEmpty(values)) {
            return;
        }
        for (var i = 0; i < values.size(); i++) {
            var value = values.get(i);
            if (value == null) {
                printWriter.write("");
            } else {
                printWriter.write(format(value));
            }

            if (i != values.size() - 1) {
                printWriter.write(SEPARATOR);
            }
        }
        printWriter.write(NEW_LINE);
    }
}