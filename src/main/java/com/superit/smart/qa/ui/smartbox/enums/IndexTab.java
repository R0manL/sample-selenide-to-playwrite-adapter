package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum IndexTab {
    GENERAL("General*"),
    CHECK("Check"),
    THRESHOLD("Threshold"),
    LEASE_INDEX("Lease index*");

    private final String title;

    IndexTab(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}