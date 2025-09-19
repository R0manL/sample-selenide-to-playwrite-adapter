package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum WidgetID {
    ACQUISITION("acquisition"),
    ADDITIONAL_INFORMATION("additional information"),
    ADMINISTRATION("administration"),
    APPRAISAL_MASTER_DATA("appraisal smart data"),
    ASSET_INFORMATION("asset information"),
    ASSET_MANAGEMENT("asset management"),
    ASSET_MANAGEMENT_ACTIVITIES("asset management activities"),
    CATEGORY("category"),
    COMMENT("comment"),
    COMPANY("company"),
    CONDITIONS("conditions"),
    CONTRACT_CONDITIONS("contract conditions"),
    CURRENCY_RATE_FIXING("currency rates (fixing)"),
    CURRENCY_RATE_PLAN("currency rates (plan)"),
    DCF("dcf"),
    DEBITS("debits"),
    DELIVERABLES("deliverables"),
    DEPOSITS("deposits"),
    DERIVATIVES("derivatives"),
    DISCOUNT_PREMIUM_DETAIL("discount/premium detail"),
    END("end"),
    EXTENSION_OPTION("extension option"),
    FINANCE_AND_ACCOUNTING("finance & accounting"),
    GENERAL("general"),
    IDENTIFICATION("identification"),
    INDEX_AGREEMENTS("index agreements"),
    INDEX_SERIES("index series"),
    INDEX_VALUES("index values"),
    INVESTMENTS_LINKS("investments links"),
    KEY_FACTS("key facts"),
    KEY_SETTINGS("key settings"),
    LINKS("links"),
    LOAN_BALANCE("loan balance"),
    LOCATION("location"),
    LTV("ltv"),
    MAINTENANCE_DETAILS("maintenance details"),
    MARKET_VALUE_DETAILS("market value details"),
    MEASURES("measures"),
    OPTION("option"),
    PARAMETER("parameter"),
    RATINGS("ratings"),
    REFERENCES("references"),
    REPORTING_SYSTEM("reporting system"),
    RESPONSIBILITIES("responsibilities"),
    RESULTS_PRESENTATION("results presentation"),
    ROLES("roles"),
    SALE("sale"),
    SHAREHOLDINGS("shareholdings"),
    SHORTENING_OPTION("shortening option"),
    SPECIFICATIONS("specifications"),
    STRATEGIES("strategies"),
    SYNDICATED_LOANS("syndicated loans"),
    TECHNICAL_IDS("technical ids"),
    TERMINATION_RIGHTS("termination rights"),
    THRESHOLD("threshold"),
    TIMING_INFORMATION("timing information"),
    TYPE("type"),
    VALUES("values");

    private final String id;

    WidgetID(@NotNull String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
