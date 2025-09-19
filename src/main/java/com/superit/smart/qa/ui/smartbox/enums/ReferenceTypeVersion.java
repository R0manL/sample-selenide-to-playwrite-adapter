package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum ReferenceTypeVersion {
    G01_BRANCH("G01 branch"),
    G02_SUBSIDARY("G02 subsidary"),
    R01_REPLACEMENT_1("R01 Replacement 1"),
    R02_REPLACEMENT_2("R02 Replacement 2"),
    TO_TAX_OFFICE("TO Tax Office");

    private final String value;

    ReferenceTypeVersion(@NotNull String value) {
        this.value = value;
    }
}
