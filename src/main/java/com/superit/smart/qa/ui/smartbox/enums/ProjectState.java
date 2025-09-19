package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ProjectState {
    CAN_BE_MEASURE("00 Can- be measure"),
    MUST_BE_MEASURE("10 Must- be measure");

    private final String title;

    ProjectState(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
