package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by R0manL.
 */

public enum TableColumnTitle {
    ASSET_ID("Asset ID"),
    ASSET_CURRENCY("Asset currency");

    private final String title;


    TableColumnTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public String getTitle() {
        return this.title;
    }

    public static TableColumnTitle from(String text) {
        return Arrays.stream(values())
                .filter(title -> title.getTitle().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No title with text '" + text + "' has been found."));
    }

    @Override
    public String toString() {
        return this.title;
    }
}
