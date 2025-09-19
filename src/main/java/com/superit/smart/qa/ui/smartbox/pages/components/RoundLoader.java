package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.Nullable;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


public class RoundLoader {

    private static final String LOADER_CSS_LOCATOR = "control-it-loader .loader";

    public WebElm getRoundLoaderElm(@Nullable WebElm parentElm) {
        return parentElm == null ? $(LOADER_CSS_LOCATOR) : parentElm.$(LOADER_CSS_LOCATOR);
    }
}
