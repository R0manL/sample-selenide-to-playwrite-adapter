package com.superit.smart.qa.ui.smartbox.pages;


import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class DashboardPage extends CockpitPage {


    private DashboardPage() {
        // None
    }

    public static DashboardPage getDashboardPage() {
        return new DashboardPage();
    }

    public WebElm getDescriptionElm() {
        return $("//p[@class='text-box-description']");
    }
}