package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ReleaseState {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    DIRECT_RESULT_INPUT("Direct Result Input"),
    CLOSED("Closed");

    private final String title;

    ReleaseState(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
