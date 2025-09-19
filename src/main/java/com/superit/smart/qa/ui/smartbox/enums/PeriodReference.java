package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum PeriodReference {
    NONE("None"),
    MONTHLY("1 = monthly"),
    YEARLY("12 = yearly");

    private final String text;

    PeriodReference(@NotNull String text) {
        this.text = text;
    }

    @NotNull
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getText();
    }
}
