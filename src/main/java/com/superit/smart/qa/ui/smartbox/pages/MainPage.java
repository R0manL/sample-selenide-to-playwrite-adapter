package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.options.BoundingBox;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.pw;

/* This is root element for (page that covers) all pages. */
public class MainPage {
    private static final WebElm ROOT_ELM = $("control-it-main-page");

    public void clickAtTopRightCorner() {
        BoundingBox box = ROOT_ELM.getLocator().boundingBox();
        pw().getPage().mouse().click(box.width - 1, 0);
    }
}
