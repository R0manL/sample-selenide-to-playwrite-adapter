package com.superit.smart.qa.ui.smartbox.pages.messeges;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.hidden;
import static com.superit.smart.qa.core.playwright.conditions.Condition.noneVisible;


@Slf4j
public class ErrorNotificationMsg {
    private static final WebElm ERROR_MSG_1_ELM = $(".error-list");
    private static final WebElm ERROR_MSG_2_ELM = $("//control-it-confirm-modal//*[text()='Sorry, something went wrong']");

    private ErrorNotificationMsg() {
    }

    public static void checkErrorNotification() {
        ERROR_MSG_1_ELM.shouldBe(noneVisible(), Duration.ofMillis(1)); //Note. Test must fail immediately when error appears.
        ERROR_MSG_2_ELM.shouldBe(hidden);
    }
}