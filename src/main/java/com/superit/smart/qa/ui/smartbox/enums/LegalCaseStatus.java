package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public enum LegalCaseStatus {
    ACTIVE("10 Active"),
    INACTIVE("20 Inactive"),
    AT_ISSUE("30 At Issue"),
    DONE("40 Done");

    private final String value;

    LegalCaseStatus(@NotNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}