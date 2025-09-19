package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum TypeOfDate {
    YEARS("Years", "app-dropDown-option-years"),
    QUARTERS("Quarters", "app-dropDown-option-quarters"),
    MONTHS("Months", "app-dropDown-option-months");

    private final String typeValue;
    private final String dataAttrQAValue;

    TypeOfDate(@NotNull String typeValue, @NotNull String dataAttrQAValue) {
        this.typeValue = typeValue;
        this.dataAttrQAValue = dataAttrQAValue;
    }

    @NotNull
    public String getTypeValue() {
        return this.typeValue;
    }

    @NotNull
    public String getDataAttrQAValue() {
        return this.dataAttrQAValue;
    }

    @Override
    public String toString() {
        return getTypeValue();
    }
}
