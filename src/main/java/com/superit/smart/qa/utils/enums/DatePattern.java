package com.superit.smart.qa.utils.enums;

import org.jetbrains.annotations.NotNull;

/**
 * Created by R0manL.
 */

public enum DatePattern {
    UI_DATE_FORMAT("dd.MM.yyyy"),
    UI_YYYY_MM_DD("yyyyMMdd"),
    UI_YYYY_MM("yyyyMM"),
    UI_CALCULATION_STATE("dd.MM.yyyy HH:mm"),
    UI_COLLAPP_LOG_MSG("dd.MM.yy HH:mm"),
    API_ENTITY_DATE_FORMAT("yyyy-MM-dd"),
    API_TIME_FORMAT("uuuu-MM-dd'T'HH:mm:ss.SSSX"),
    API_TIME_FORMAT_END_WIDGET("uuuu-MM-dd'T'HH:mm:ss'Z'");


    private final String pattern;

    DatePattern(@NotNull String pattern) {
        this.pattern = pattern;
    }

    @NotNull
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String toString() {
        return getPattern();
    }
}
