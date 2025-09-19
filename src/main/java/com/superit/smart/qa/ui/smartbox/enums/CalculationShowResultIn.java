package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


/**
 * Created on 02.08.2022
 */

public enum CalculationShowResultIn {
    YEARS_MONTH("Years - months"),
    YEARS_QUARTERS("Years - quarters"),
    QUARTERS_MONTHS("Quarters - months"),
    MONTHS("Months");

    private final String title;

    CalculationShowResultIn(@NotNull String title) {
        this.title = title;
    }

    public String getValue() {
        return this.title;
    }
}
