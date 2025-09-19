package com.superit.smart.qa.ui.smartbox.pages;


import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.menus.NotificationsLeftMenu;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.hidden;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.ui.smartbox.pages.AssetsPage.getAssetsPage;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class SideBar {
    private static final WebElm SIDE_BAR_ELM = $(".sidebar");
    private static final WebElm SIDE_BAR_EXPANDED_ELM = $("div.side-bar-expanded-backdrop");


    private SideBar() {
        // None
    }

    public static SideBar getSideBar() {
        return new SideBar();
    }

    public WebElm getSideBarElm() {
        return SIDE_BAR_ELM;
    }

    public SideBar open(MenuModule menuModule) {
        waitOnLoadersDisappearAndCheckErrors();
        $(menuModule.getCssSelector())
                .click();

        SIDE_BAR_EXPANDED_ELM.shouldBe(visible);
        sleepForWebElmRefreshDuration(4);

        return new SideBar();
    }

    public AssetsPage searchAndSelectItem(@NotNull String item) {
        new SideBar()
                .selectAllItems()
                .searchItem(item)
                .selectItem(item);

        return getAssetsPage();
    }

    private SideBar selectAllItems() {
        $(".group-item.all-items .group-name").hover().click();
        sleepForWebElmRefreshDuration(4);

        return this;
    }

    private SideBar searchItem(@NotNull String item) {
        WebElm searchInput = new InputWithPlaceholderWrapper(InputPlaceholder.FIND_MENU, $("control-it-sidebar-menu-group-items")).getInput();
//        WebElm searchInput = $("input[placeholder='Find menu']").shouldBe(visible);
        searchInput.shouldBe(visible);
        sleepForWebElmRefreshDuration();
        searchInput.val(item);

        return this;
    }

    private void selectItem(@NotNull String item) {
        $("//*[contains(@class, 'item-card') and .//*[normalize-space(text())='" + item + "']]//a[@class='link-layer']")
                .click();

        waitOnLoadersDisappearAndCheckErrors();
    }

    public WebElm getModuleElm(MenuModule menuModule) {
        log.info("Get '{}' module element.", menuModule.getCaption());
        return $(menuModule.getCssSelector());
    }

    public NotificationsLeftMenu openNotificationsLeftMenu() {
        WebElm notificationBtn = $(".sidebar-link .icon-notification");
        notificationBtn.hover();
        SIDE_BAR_EXPANDED_ELM.shouldBe(visible);
        notificationBtn.click();

        return new NotificationsLeftMenu();
    }

    public void clickLogout() {
        $(".icon-logout").click();
        $(".table .table-row").click();
    }

    public void collapseSideBar() {
        if (SIDE_BAR_EXPANDED_ELM.isDisplayed()) {
            removeFocus();
            SIDE_BAR_EXPANDED_ELM.shouldBe(hidden);
        } else {
            log.warn("Side bar doesn't visible. Skip collapsing.");
        }
    }
}