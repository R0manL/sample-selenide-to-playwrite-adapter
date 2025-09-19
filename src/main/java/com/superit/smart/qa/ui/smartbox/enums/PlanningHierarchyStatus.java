package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum PlanningHierarchyStatus {
    UNIT_USE_TYPE("Unit use type"),
    ASSET_USE_TYPE("Asset & Use Type"),
    LEASE("Lease"),
    MASTER_DATA("Master data"),
    CONTRACT_CONTRACT_CONDITION("Contract/Contract condition");

    private final String text;

    PlanningHierarchyStatus(@NotNull String text) {
        this.text = text;
    }

    @NotNull
    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
