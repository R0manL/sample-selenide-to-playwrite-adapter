package com.superit.smart.qa.ui.smartbox.pages.components;


import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class TippyToolTipWrapper {
    private static final WebElm TIPPY_TOOLTIP_ELM = $(".tippy-popper:not(.is-hidden) .tippy-tooltip");


    public WebElm getRootElm() {
        return TIPPY_TOOLTIP_ELM;
    }

    public WebElm getContentElm() {
        return getRootElm().$(".tippy-content");
    }

    public WebElm getTitleElm() {
        return getContentElm().$(".tippy-custom-title");
    }
}
