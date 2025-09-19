package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ContractConditionsTab {
    BOOK_VALUE("Book value"),
    DEADLINE_MANAGEMENT("Deadline management*"),
    GENERAL("General*"),
    INDEXATION("Indexation"),
    LIQUIDITY("Liquidity*"),
    MAINTENANCE("Maintenance"),
    OPEX("OPEX"),
    VALUES("Values*");

    private final String title;

    ContractConditionsTab(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}