package com.superit.smart.qa.ui.smartbox.enums;

public enum CheckboxCaption {
    DONT_SHOW_AGAIN("Don't show again");


    private final String caption;

    CheckboxCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
