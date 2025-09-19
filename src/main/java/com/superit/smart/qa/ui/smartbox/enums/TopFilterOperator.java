package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum TopFilterOperator {
    GREATER_THAN("greater than"),
    IN_LIST("in list"),
    EQUAL_TO("equal to"),
    NOT_IN_LIST("not in list"),
    CONTAINS("contains"),
    IN_BETWEEN("in between"),
    GREATER_OR_EQUAL("greater or equal"),
    EMPTY("empty"),
    NOT_EMPTY("not empty"),
    LESS_OR_EQUAL("less or equal");

    private final String operator;

    TopFilterOperator(@NotNull String operator) {
        this.operator = operator;
    }

    @NotNull
    public String getName() {
        return this.operator;
    }

    @Override
    public String toString() {
        return getName();
    }
}
