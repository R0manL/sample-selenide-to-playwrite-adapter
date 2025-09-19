package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public enum Role {
    PR80_TENANT("PR80 Tenant"),
    PR170_LEGAL_PARTNER("PR170 Legal Partner"),
    PR180_INSURANCES_PARTNER("PR180 Insurances Partner");

    private final String value;

    Role(@NotNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}