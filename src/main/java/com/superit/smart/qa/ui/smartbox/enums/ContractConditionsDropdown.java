package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ContractConditionsDropdown {
    BUDGET_GROUP("Budget group:"),
    CHECK_BY_INTERVAL("Check by Interval:"),
    DATA_LEVEL_FOR_REFERENCE("Data level for reference:"),
    ENTITY_FOR_REFERENCE("Entity for reference:"),
    INDEX_SERIES("Index series:"),
    PAYMENT_INTERVAL("Payment interval: *"),
    PAYMENT_MODE("Payment mode:"),
    PROJECT_STATE("Project state:"),
    REFERENCE("Reference:"),
    TIME_SPAN_OF_VALUES("Time span of values: *"),
    ACTIVE_INDICATOR("Active indicator:");

    private final String title;

    ContractConditionsDropdown(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}