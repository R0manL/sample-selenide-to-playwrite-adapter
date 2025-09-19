package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum TopFilterValue {
    // Date values
    DATE_CURRENT_MONTH("@currentMonth"),

    // Fund type values
    FUND_TYPE_OF_OPEN("OF Open");

    private final String text;

    TopFilterValue(@NotNull String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
