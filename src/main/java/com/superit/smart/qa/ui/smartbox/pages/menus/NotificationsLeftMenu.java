package com.superit.smart.qa.ui.smartbox.pages.menus;


import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.CockpitPage;
import com.superit.smart.qa.ui.smartbox.pages.components.NotificationGroup;
import com.superit.smart.qa.ui.smartbox.pages.components.NotificationItem;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.utils.DateUtils.convertToDifferentFormat;


@Slf4j
public class NotificationsLeftMenu extends CockpitPage {
    private static final WebElm MENU_ROOT_ELM = $("control-it-side-bar-notifications .app-aside");
    private static final WebElm NOTIFICATION_ITEMS = MENU_ROOT_ELM.$$("control-it-notification-card");
    private static final WebElm NOTIFICATION_GROUPS = MENU_ROOT_ELM.$$("control-it-notification-group");


    public NotificationsLeftMenu() {
        MENU_ROOT_ELM.shouldBe(visible);
    }

    public void clickClose() {
        log.info("Close notification menu.");

        MENU_ROOT_ELM.$(".icon-closed").click();
    }

    public WebElm getNotificationItems() {
        return NOTIFICATION_ITEMS;
    }

    public List<NotificationItem> getNotificationItemsAfter(LocalDateTime value) {
        LocalDateTime searchDate = convertToDifferentFormat(value, DatePattern.UI_CALCULATION_STATE);

        return getNotificationItems()
                .all()
                .stream()
                .filter(elm -> !(new NotificationItem(elm).getDate().isBefore(searchDate)))
                .map(NotificationItem::new)
                .collect(Collectors.toList());
    }

    public List<NotificationGroup> getNotificationGroupsAfter(LocalDateTime localDateTime) {
        LocalDateTime searchDate = convertToDifferentFormat(localDateTime, DatePattern.UI_CALCULATION_STATE);

        return NOTIFICATION_GROUPS
                .all()
                .stream()
                .filter(elm -> !(new NotificationGroup(elm).getDate().isBefore(searchDate)))
                .map(NotificationGroup::new)
                .collect(Collectors.toList());
    }
}