package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.PlaywrightWrapper;
import com.superit.smart.qa.ui.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BBoxLoginPage extends LoginPage {
    private static final String BBOX_URL = Configuration.BASE_URL;


    private BBoxLoginPage() {
        // NONE
    }

    public static BBoxLoginPage getBBoxLoginPage() {
        return new BBoxLoginPage();
    }

    public BBoxLoginPage open() {
        log.info("Open smartbox login page.");
        PlaywrightWrapper.open(BBOX_URL);

        return this;
    }

    public void openNewPage() {
        log.info("Open a new smartbox login page.");
        PlaywrightWrapper.openNewPage(BBOX_URL);
    }
}