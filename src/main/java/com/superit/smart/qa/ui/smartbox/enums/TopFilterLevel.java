package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

public enum TopFilterLevel {

    PROPERTIES("Properties"),
    ASSETS("Assets"),
    UNITS("Rental Units"),
    PORTFOLIO("Funds | Portfolios | Mandates"),
    LEASES("Lease Contracts"),
    COMPANIES("Companies");

    private final String topLevelName;

    TopFilterLevel(@NotNull String topLevelName) {
        this.topLevelName = topLevelName;
    }

    @Override
    public String toString() {
        return this.topLevelName;
    }
}
