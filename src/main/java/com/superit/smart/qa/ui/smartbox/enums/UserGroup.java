package com.superit.smart.qa.ui.smartbox.enums;

public enum UserGroup {

    BISUSER("BISUSER");

    private final String groupName;

    UserGroup(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return getGroupName();
    }

    public String getGroupName() {
        return this.groupName;
    }
}
