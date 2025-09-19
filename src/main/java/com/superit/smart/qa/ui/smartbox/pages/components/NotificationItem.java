package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.superit.smart.qa.utils.DateUtils.convertStringToLocalDateTimeInFormat;

@Slf4j
public class NotificationItem {
    private final WebElm rootElm;


    public NotificationItem(WebElm rootElm) {
        this.rootElm = rootElm;
    }

    public LocalDateTime getDate() {
        log.info("Get notification item's date.");
        String dateTime = rootElm.$(".n-text-date").getOwnText();

        return convertStringToLocalDateTimeInFormat(dateTime, DatePattern.UI_CALCULATION_STATE);
    }

    public String getDescription() {
        log.info("Get notification item's description.");
        return rootElm.$(".n-list-item-description").getOwnText();
    }
}