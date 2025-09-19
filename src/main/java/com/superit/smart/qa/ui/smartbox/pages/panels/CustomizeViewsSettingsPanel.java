package com.superit.smart.qa.ui.smartbox.pages.panels;


import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class CustomizeViewsSettingsPanel extends SettingsPanel {
    private static final WebElm ROOT_ELM = $("control-it-customization-view-modal");


    @Override
    protected WebElm getRootElm() {
        return ROOT_ELM;
    }
}
