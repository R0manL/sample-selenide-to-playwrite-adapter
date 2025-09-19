package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum Currency {
    EUR("EUR European Euro"),
    GBP("GBP British Pound"),
    UAH("UAH Ukrainian hryvnia");

    private final String text;

    Currency(@NotNull String text) {
        this.text = text;
    }
}