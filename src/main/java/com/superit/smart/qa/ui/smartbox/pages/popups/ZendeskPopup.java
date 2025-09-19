package com.superit.smart.qa.ui.smartbox.pages.popups;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


@Slf4j
public class ZendeskPopup {
    private static final WebElm ROOT_ELM = $("control-it-zendesk-popup");
    private static final WebElm GOT_IT_BTN = ROOT_ELM.$("button.zendesk-confirm-button");


    private ZendeskPopup() {
        // NONE
    }

    public static ZendeskPopup getZendeskPopup() {
        return new ZendeskPopup();
    }

    public WebElm getGotItBtn() {
        return GOT_IT_BTN;
    }
}
