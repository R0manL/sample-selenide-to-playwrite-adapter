package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.superit.smart.qa.ui.smartbox.enums.EmptyFieldText.SELECT;

public enum OptionHolder {
    EMPTY(SELECT.getText()),
    BOTH("B Both"),
    TENANT("M Tenant"),
    LANDLORD("V Landlord");

    private final String title;

    OptionHolder(@NotNull String title) {
        this.title = title;
    }

    public static OptionHolder from(@NotNull String text) {
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
