package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum FundStrategyTargetAllocation {
    AIFM_STRATEGY("AIFM Strategy"),
    ASSET_REGION("Asset Region"),
    COUNTRIES("Countries"),
    FINANCIAL_KEY_FIGURES("Financial Key Figures"),
    FUNDS_VOLUME("Funds Volume"),
    INREV_FUNDSSTRATEGY("INREV Fundsstrategy"),
    MAIN_USE_TYPE_BVI("Main Use Type BVI");

    private final String versionText;

    FundStrategyTargetAllocation(@NotNull String versionText) {
        this.versionText = versionText;
    }

    @NotNull
    public String getString() {
        return this.versionText;
    }
}