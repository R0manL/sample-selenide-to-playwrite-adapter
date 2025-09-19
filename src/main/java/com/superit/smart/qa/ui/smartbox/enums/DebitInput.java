package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum DebitInput {
    START("Start: *"),
    END("End: *"),
    VALUE("Value: *"),
    COMMENT_FOR_DEBIT("Comment for debit:");

    private final String title;

    DebitInput(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}