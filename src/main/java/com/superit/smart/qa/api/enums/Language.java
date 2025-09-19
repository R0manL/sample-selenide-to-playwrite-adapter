package com.superit.smart.qa.api.enums;

import org.jetbrains.annotations.NotNull;

public enum Language {
    EN("en"),
    DE("de");

    private final String lang;

    Language(@NotNull String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return this.lang;
    }
}
