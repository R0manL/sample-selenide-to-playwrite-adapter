package com.superit.smart.qa.core.playwright;

import org.jetbrains.annotations.NotNull;

public enum ModifierKey {
    ENTER("Enter"),
    TAB("Tab"),
    ESCAPE("Escape"),
    DELETE("Delete"),
    BACKSPACE("Backspace"),
    CONTROL("Control"),
    ALT("Alt"),
    END("End"),

    ARROW_LEFT("ArrowLeft"),
    F9("F9");

    private final String name;

    ModifierKey(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String toString() {
        return this.name;
    }
}
