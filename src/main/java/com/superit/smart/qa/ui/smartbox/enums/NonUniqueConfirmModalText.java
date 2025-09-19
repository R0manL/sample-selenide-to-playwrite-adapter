package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum NonUniqueConfirmModalText {
    NON_UNIQUE_DATA("NON-UNIQUE DATA"),
    NON_UNIQUE_IDENTIFICATION ("Non-unique identification."),
    COMBINATION_EXISTS ("The combination of the following values already exists - please check the recycle bin or change at least one of the following parameters:");

    private final String text;

    NonUniqueConfirmModalText(@NotNull String text) {
        this.text = text;
    }
}
