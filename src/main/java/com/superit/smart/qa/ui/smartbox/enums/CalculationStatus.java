package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum CalculationStatus {
    DIRTY("Dirty"),
    CLEAN("Clean"),
    IN_PROGRESS("In Progress");

    private final String title;

    CalculationStatus(@NotNull String title) {
        this.title = title;
    }

    public String getText() {
        return this.title;
    }
}
