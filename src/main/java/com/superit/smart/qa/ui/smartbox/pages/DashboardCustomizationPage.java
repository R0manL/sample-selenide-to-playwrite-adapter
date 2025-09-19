package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.ui.smartbox.pages.DashboardPage.getDashboardPage;

@Slf4j
public class DashboardCustomizationPage {
    private static final WebElm ROOT_ELEMENT = $("control-it-customization-screen");


    private DashboardCustomizationPage() {
        // NONE
    }

    public static DashboardCustomizationPage getDashboardCustomizationPage() {
        return new DashboardCustomizationPage();
    }

    public DashboardPage clickBackToDashboard() {
        log.info("Click back to dashboard (cancel customization).");
        ROOT_ELEMENT.$("button[data-attr-qa='cancel-edit-btn']").click();

        return getDashboardPage();
    }
}
