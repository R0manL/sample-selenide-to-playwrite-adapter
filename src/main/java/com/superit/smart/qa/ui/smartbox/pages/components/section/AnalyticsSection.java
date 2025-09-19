package com.superit.smart.qa.ui.smartbox.pages.components.section;


import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

/**
 * Created by R0manL.
 */

public class AnalyticsSection {
    private static final WebElm ROOT_CONTAINER_ELM = $("control-it-analytics .analytics-section");


    public WebElm getRootContainerElm() {
        return ROOT_CONTAINER_ELM;
    }
}
