package com.superit.smart.qa.ui.smartbox.pages.messeges;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.utils.WaitUtils.waitOnWebElmMightAppearThenDisappear;

@Slf4j
public class NoneEditNotificationMsg {
    private static final WebElm NOTIFICATION_ROOT_ELM = $("control-it-none-edit-notification .notification-modal");


    private NoneEditNotificationMsg() {
    }

    public static NoneEditNotificationMsg getNoneEditNotificationMsg() {
        return new NoneEditNotificationMsg();
    }

    public void waitOnNotificationMightAppearsThenDisappear() {
        waitOnWebElmMightAppearThenDisappear(NOTIFICATION_ROOT_ELM, ENV_CONFIG.webElmRefreshDuration().multipliedBy(4));
    }
}
