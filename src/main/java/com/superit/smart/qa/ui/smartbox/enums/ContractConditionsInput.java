package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ContractConditionsInput {
    APPOINTMENT_RATE("Apportionment rate (in %):"),
    CHECK_BY_INTERVAL("Check by Interval:"),
    CONDITION_ID("Condition ID: *"),
    DATE_OF_ACCEPTANCE_RENEWAL_OPTION("Date of acceptance of renewal option:"),
    DEPRECATION_PERIOD("Depreciation period (total):"),
    DEPRECIATION_VALUE("Depreciation value:"),
    END("End:"),
    END_ACTUAL("End (actual):"),
    FACTOR_ON_CASHFLOW("Factor on cashflow:"),
    FACTOR_ON_EARNINGS("Factor on earnings:"),
    FIXED_BASE_FOR_DEPRECATION("Fixed base for depreciation:"),
    FIXED_VALUE("Fixed value:"),
    INDEXATION_FROM("Indexation from:"),
    INDEX_INCREASE_VALUE("Index increase value:"),
    LIABILITY("Liability:"),
    MAXIMUM("Maximum:"),
    MINIMUM("Minimum:"),
    MULTIPLIER_ON_REFERENCE("Multiplier on reference:"),
    REFERENCE("Reference:"),
    NOTICE_PERIOD_IN_MONTHS("Notice period (in months): *"),
    PAYMENT_OFFSET("Payment offset:"),
    REMINDER_OF_TERMINATION_DATE("Reminder of termination date / end of term:"),
    START("Start: *"),
    START_ACTUAL("Start (actual):"),
    VACANCY_COST_RATE("Vacancy cost rate (in %):"),
    BUSINESS_PLAN("Business plan:"),
    ABSOLUTE_CONSTRUCTION_MANAGEMENT_FEE("Absolute construction management fee:"),
    PERCENTAL_CONSTRUCTION_MANAGEMENT_FEE("Percental construction management fee:");

    private final String title;


    ContractConditionsInput(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}