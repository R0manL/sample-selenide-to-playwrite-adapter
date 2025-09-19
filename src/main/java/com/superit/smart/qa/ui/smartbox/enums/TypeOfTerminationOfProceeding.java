package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public enum TypeOfTerminationOfProceeding {
    JUDGMENT("10 Judgment"),
    COMPROMISE("20 Compromise"),
    OTHER("30 Other");

    private final String value;

    TypeOfTerminationOfProceeding(@NotNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}