package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public enum InsuranceStatus {
    ESTABLISHED("10 established"),
    DISPLAYED("20 displayed"),
    BALANCED("30 balanced"),
    DONE("40 done");

    private final String value;

    InsuranceStatus(@NotNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}