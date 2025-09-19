package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum PlanningHistoryStatus {
    DELETE("Delete"),
    UPDATE("Update");

    private final String text;

    PlanningHistoryStatus(@NotNull String text) {
        this.text = text;
    }

    @NotNull
    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}