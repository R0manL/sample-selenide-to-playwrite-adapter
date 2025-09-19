package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum BaseVersion {
    TOP_FILTER_SESSION("@Top filter version");

    private final String typeValue;

    BaseVersion(@NotNull String typeValue) {
        this.typeValue = typeValue;
    }

    @Override
    public String toString() {
        return getTypeValue();
    }
}
