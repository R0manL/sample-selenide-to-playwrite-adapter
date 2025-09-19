package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


@Slf4j
public class CellHistoryRightMenu {
    private static final WebElm ROOT_ELM = $(".app-aside.is-right");
    private static final String PREVIOUS_TEXT = "Previous";
    private static final String CHANGE_TO_TEXT = "Changed to";

    public void clickClose() {
        log.info("Click 'Close' icon on Cell History Right Menu");
        ROOT_ELM.$(".icon-closed").click();
    }

    public WebElm getUserInfoElms() {
        return ROOT_ELM.$$(".details-footer-title");
    }

    public WebElm getStatusValueElms() {
        return ROOT_ELM.$$("//*[@class='details-list-value-title' and text()='Status:']/../*[@class='details-list-value']");
    }

    public WebElm getPreviousValueElms() {
        return ROOT_ELM.$$("//*[@class='details-list-value-title' and text()='" + PREVIOUS_TEXT + ":']/../*[@class='details-list-value']");
    }

    public WebElm getChangedToValueElms() {
        return ROOT_ELM.$$("//*[@class='details-list-value-title' and text()='" + CHANGE_TO_TEXT + ":']/../*[@class='details-list-value']");
    }
}
