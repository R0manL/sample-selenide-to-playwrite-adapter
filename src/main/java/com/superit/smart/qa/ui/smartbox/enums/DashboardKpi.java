package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum DashboardKpi {

    RENTAL_UNITS("# Rental units (RU)"),
    ASSET_MANAGER("Asset manager, general"),
    LETTABLE_AREA("Lettable area"),
    VACANCY_RATE("Vacancy rate (# RUs)");


    private final String kpiName;

    DashboardKpi(@NotNull String kpiName) {
        this.kpiName = kpiName;
    }

    @NotNull
    public String getKpiName() {
        return this.kpiName;
    }

    @Override
    public String toString() {
        return this.kpiName;
    }
}
