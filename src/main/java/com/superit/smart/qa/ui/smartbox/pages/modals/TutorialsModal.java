package com.superit.smart.qa.ui.smartbox.pages.modals;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;


@Slf4j
public class TutorialsModal {
    private static final WebElm ROOT_ELEMENT = $("control-it-tutorials-modal");

    public void clickClose() {
        log.info("Click close widget.");

        ROOT_ELEMENT.$(".modal-head-btn button .icon-closed").click();
        ROOT_ELEMENT.should(disappear);
    }
}
