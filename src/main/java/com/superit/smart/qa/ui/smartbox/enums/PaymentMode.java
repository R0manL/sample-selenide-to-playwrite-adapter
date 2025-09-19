package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum PaymentMode {
    IN_ARREAS("0 In arrears"),
    IN_ADVANCE("1 In advance");

    private final String title;

    PaymentMode(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
