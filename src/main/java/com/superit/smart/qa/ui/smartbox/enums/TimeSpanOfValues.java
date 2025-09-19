package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum TimeSpanOfValues {
    ONE_TIME("0 one-time"),
    MONTHLY("1 monthly"),
    ANNUALLY("12 annually"),
    QUARTERLY("3 quarterly"),
    HALF_YEARLY("6 half-yearly");

    private final String title;

    TimeSpanOfValues(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
