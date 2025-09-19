package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DebitGroupOfCheckboxes {
    MONTHS("Months: *");

    private final String caption;

    DebitGroupOfCheckboxes(@NotNull String caption) {
        this.caption = caption;
    }
}