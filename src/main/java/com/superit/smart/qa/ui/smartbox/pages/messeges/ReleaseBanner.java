package com.superit.smart.qa.ui.smartbox.pages.messeges;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


@Slf4j
public class ReleaseBanner {
    private static final WebElm BANNER_ROOT_ELM = $("control-it-banner-release-notes");

    private ReleaseBanner() {
        // NONE
    }

    public static ReleaseBanner getReleaseBanner() {
        return new ReleaseBanner();
    }

    public boolean isReleaseBannerDisplayed() {
        return BANNER_ROOT_ELM.isDisplayed();
    }

    public void clickRemindMeLater() {
        log.info("Click 'remind me later' at release banner.");
        BANNER_ROOT_ELM.$("//button[normalize-space(text())='Remind me later']").click();
    }

    public void clickRemindMeLaterIfExist() {
        log.debug("Check if 'new release' banner has appeared.");
        if(isReleaseBannerDisplayed()) {
            clickRemindMeLater();
        }
    }
}
