package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum TopFilterCriteria {
    ACQ_ECONOMIC_TRANSITION_DATE("Acq. economic transition date"),
    ASSET_ID("Asset ID"),
    ASSET_TYPE("Asset type"),
    COMPRESSED_TYPE_OF_USE("Compressed Type of Use"),
    DEFINITE_END_DATE("Definite end date (terminated as of)"),
    EXTERNAL_ID("External ID"),
    FUND_TYPE("Fund type"),
    COMPANY_ID("Company ID"),
    KPI("KPI"),
    PORTFOLIO_ID("Portfolio ID"),
    TYPE_OF_USE("Type of use"),
    UNIT_END("Unit end"),
    UNIT_START("Unit start"),
    VACANCY_CONTRACT("Vacancy contract");

    private final String name;

    TopFilterCriteria(@NotNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
