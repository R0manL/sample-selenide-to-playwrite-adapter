package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;


public class ProgressBar {

    private static final String PROGRESS_BAR_CSS_LOCATOR = ".progress-bar";

    public WebElm getProgressBar(WebElm parentElm) {
        return parentElm.$(PROGRESS_BAR_CSS_LOCATOR);
    }
}
