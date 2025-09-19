package com.superit.smart.qa.utils.enums;

import org.jetbrains.annotations.NotNull;

public enum DecimalFormat {
    THOUSAND_COMMA_FORMAT("#,###.00"),
    TWO_DIGITS_AFTER_DOT("###.00");

    private final String getFormat;

    DecimalFormat(@NotNull String format) {
        this.getFormat = format;
    }

    public String getFormat() {
        return this.getFormat;
    }
}
