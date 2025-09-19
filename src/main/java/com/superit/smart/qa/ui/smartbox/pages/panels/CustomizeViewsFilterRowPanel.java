package com.superit.smart.qa.ui.smartbox.pages.panels;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ConfirmModalWrapper;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.enabled;

@Slf4j
public class CustomizeViewsFilterRowPanel {
    private static final WebElm ROOT_ELM = $("control-it-customization-screen .filter-row");

    public CustomizeViewsFilterRowPanel clickCreateCustomizationView() {
        ROOT_ELM.$("//span[@class='icon-plus']")
                .shouldBe(enabled)
                .click();

        return this;
    }

    public ConfirmModalWrapper clickDeleteCustomizationView() {
        ROOT_ELM.$(".icon-delete").click();

        return new ConfirmModalWrapper();
    }
}
