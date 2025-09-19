package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum DebitDropdown {
    DEBIT_POSITION_TYPE("Debit position type: *"),
    CURRENCY("Currency: *"),
    REASON_FOR_AMENDMENT("Reason for amendment:");

    private final String title;

    DebitDropdown(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}