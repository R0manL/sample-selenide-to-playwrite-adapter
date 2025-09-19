package com.superit.smart.qa.api.smartbox.enums;


/**
 * Created by R0manL.
 */

public enum UserMode {
    USER(0),
    ADMIN(1),
    GLOBAL(2);

    private final int mode;

    UserMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }
}
