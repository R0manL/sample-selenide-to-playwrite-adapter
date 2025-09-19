package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum FieldText {
    NON_EDITABLE("Non-editable"),
    YES("Yes"),
    NO("No");

    private final String text;

    FieldText(@NotNull String text) {
        this.text = text;
    }
}
