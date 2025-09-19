package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum InterestBase {
    NOT_ASSIGNED("&N.A Not assigned"),
    FIXED("0 Fixed"),
    VARIABLE("1 Variable"),
    STEADY_INTEREST_RATE_FROM_INTEREST_INDEX("4 Steady interest rate from interest index");

    private final String title;

    InterestBase(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
