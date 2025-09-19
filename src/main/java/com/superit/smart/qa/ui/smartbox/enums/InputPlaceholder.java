package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum InputPlaceholder {
    FIND_A_VIEW("Find a view…"),
    FIND_GROUP("Find Group…"),
    FIND_MENU("Find menu"),
    SEARCH("Search"),
    ENTER_NAME("Enter name");

    private final String placeholderText;

    InputPlaceholder(@NotNull String placeholderText) {
        this.placeholderText = placeholderText;
    }

    @Override
    public String toString() {
        return placeholderText;
    }
}