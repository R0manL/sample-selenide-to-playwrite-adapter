package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.Nullable;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


public class Spinner {

    private static final String ROUND_SPINNER_CSS_LOCATOR = "mat-progress-spinner";


    public WebElm getRoundProgressSpinnerElm(@Nullable WebElm parentElm) {
        return parentElm == null ? $(ROUND_SPINNER_CSS_LOCATOR) : parentElm.$(ROUND_SPINNER_CSS_LOCATOR);
    }
}
