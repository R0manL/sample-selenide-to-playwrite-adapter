package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum TypeOfCancellation {
//    NO_VALUE("Select"),
    SPECIAL_TERMINATION_RIGHT("1 Special termination right"),
    OPTION("O Option"),
    AUTOMATIC_EXTENTION("T Automatic extension"),
    OPTION_CHOSEN("Z Option chosen");

    private final String title;

    TypeOfCancellation(@NotNull String title) {
        this.title = title;
    }

    public static TypeOfCancellation from(@NotNull String text) {
        return Arrays.stream(values())
                .filter(title -> title.toString().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No title with text '" + text + "' has been found."));
    }

    @Override
    public String toString() {
        return this.title;
    }
}
