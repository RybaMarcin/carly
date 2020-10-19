package org.carly.core.shared.utils.export.support.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigDecimalFormatter implements Formatter<BigDecimal> {

    @Override
    public String format(final BigDecimal value) {
        var otherSymbols = new DecimalFormatSymbols(Locale.UK);
        var decimalFormat = new DecimalFormat("0.00", otherSymbols);
        return value != null ? decimalFormat.format(value) : "";
    }
}
