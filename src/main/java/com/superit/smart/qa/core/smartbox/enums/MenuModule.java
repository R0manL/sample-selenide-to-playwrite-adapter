package com.superit.smart.qa.core.smartbox.enums;

import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

/* Name of pages (cockpits) as defined in API (TomMenu > menu item > caption) */
public enum MenuModule {
    DASHBOARD("Dashboard", ".sidebar-link .icon-dashboard"),
    MASTER_DATA("MD", ".sidebar-link .icon-md");

    private final String caption;
    private final String cssSelector;

    MenuModule(@NotNull String caption, @NotNull String cssSelector) {
        this.caption = caption;
        this.cssSelector = cssSelector;
    }

    @NotNull
    public String getCaption() {
        return this.caption;
    }

    @NotNull
    public String getCssSelector() {
        return this.cssSelector;
    }

    @Override
    @NotNull
    public String toString() {
        return getCaption();
    }
}
