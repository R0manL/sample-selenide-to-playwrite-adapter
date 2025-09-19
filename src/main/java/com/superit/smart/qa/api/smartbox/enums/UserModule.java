package com.superit.smart.qa.api.smartbox.enums;

public enum UserModule {
    MASTER_DATA("smart-data"),
    SETTINGS("settings"),
    DASHBOARD("dashboard"),
    PLANNING("planning");

    private final String moduleName;

    UserModule(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return this.moduleName;
    }

    public String getModule() {
        return this.moduleName;
    }
}
