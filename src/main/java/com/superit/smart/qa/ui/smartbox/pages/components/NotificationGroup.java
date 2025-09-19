package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import static com.superit.smart.qa.utils.DateUtils.convertStringToLocalDateTimeInFormat;
import static com.superit.smart.qa.utils.WaitUtils.waitOnSpinnerDisappear;

@Slf4j
public class NotificationGroup {
    private final WebElm rootElm;


    public NotificationGroup(WebElm rootElm) {
        this.rootElm = rootElm;
    }

    public LocalDateTime getDate() {
        log.info("Get notification item's date.");
        DatePattern pattern = DatePattern.UI_CALCULATION_STATE;
        String dateTime = rootElm.$(".n-text-date").getOwnText();

        return convertStringToLocalDateTimeInFormat(dateTime, pattern);
    }

    public WebElm getDescriptionElm() {
        log.info("Get notification group's description.");
        return rootElm.$(".n-list-item-description");
    }

    public NotificationGroup openGroup() {
        rootElm.click();
        waitOnSpinnerDisappear(rootElm);

        return this;
    }

    public List<NotificationItem> getVisibleItems() {
        return rootElm
                .$(".n-list-group-cards-wrap")
                .$("control-it-notification-card")
                .all()
                .stream()
                .map(NotificationItem::new)
                .toList();
    }
}