package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum DashboardDeviation {
    DEV_IN_PERCENT("Dev %", "Dev %"),
    DEV_ABS("Dev abs", "Dev Abs");

    private final String text;
    private final String label;

    DashboardDeviation(@NotNull String text, @NotNull String label) {
        this.text = text;
        this.label = label;
    }

    @NotNull
    public String getText() {
        return this.text;
    }

    @NotNull
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return getText();
    }
}