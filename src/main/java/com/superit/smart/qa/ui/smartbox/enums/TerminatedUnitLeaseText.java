package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum TerminatedUnitLeaseText {
    TERMINATED_UNIT_TEXT("Unit end"),
    TERMINATED_LEASE_TEXT("Definite end date (terminated as of)");

    private final String title;

    TerminatedUnitLeaseText(@NotNull String title) {
        this.title = title;
    }

    public String getText() {
        return this.title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
