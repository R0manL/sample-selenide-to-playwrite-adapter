package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.single;

public class HomePage extends CockpitPage {


    private HomePage() {
        // None
    }

    public static HomePage getHomePage() {
        return new HomePage();
    }

    public WebElm getPageTitleElm() {
        return $(".home-widget:not(.deployment) .home-widget-title").shouldBe(single());
    }
}