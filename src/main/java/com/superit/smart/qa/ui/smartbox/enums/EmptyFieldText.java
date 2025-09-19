package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum EmptyFieldText {
    NO_DATA("No data"),
    SELECT("Select"),
    SELECT_SELECT("Select\nSelect"),
    EMPTY(""),
    ENTER("\n"),
    YES("Yes"),
    NO("No"),
    NULL("null"),
    ENTER_TEXT("Enter Text"),
    CHOOSE_DATE("Choose Date");

    private final String text;

    EmptyFieldText(@NotNull String text) {
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
