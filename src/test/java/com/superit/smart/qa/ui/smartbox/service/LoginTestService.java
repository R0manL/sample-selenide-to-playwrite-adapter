package com.superit.smart.qa.ui.smartbox.service;

import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.api.smartbox.services.UserSettingsService.*;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.ui.smartbox.pages.BBoxLoginPage.getBBoxLoginPage;
import static com.superit.smart.qa.ui.smartbox.pages.SideBar.getSideBar;
import static com.superit.smart.qa.ui.smartbox.pages.popups.ZendeskPopup.getZendeskPopup;
import static com.superit.smart.qa.utils.WaitUtils.waitOnRoundLoaderMightAppearThenDisappear;
import static com.superit.smart.qa.utils.WaitUtils.waitOnWebElmMightAppearsThenClickOnIt;

/**
 * Created by R0manL.
 */

@Slf4j
public class LoginTestService {

    private LoginTestService() {
        // None
    }

    public static void loginAs(User user, MenuModule menuModule) {
        new LoginTestService().resetPreviousSessionForUserAndModule(user, menuModule);
        getBBoxLoginPage()
                .open()
                .loginAs(user);
    }

    public static void loginAndOpen(MenuModule menuModule) {
        loginAndOpen(User.DEFAULT_USER, menuModule);
    }

    public static void loginAndOpen(User user, MenuModule menuModule) {
        loginAs(user, menuModule);
        closeZendeskPopup();
        getSideBar().open(menuModule);
    }

    public static void navigateToModuleWithoutLoginAs(MenuModule menuModule) {
        new LoginTestService().resetPreviousSessionForUserAndModule(User.DEFAULT_USER, menuModule);
        getBBoxLoginPage().open();
        waitOnRoundLoaderMightAppearThenDisappear();
        closeZendeskPopup();
        getSideBar().open(menuModule);
    }

    private void resetPreviousSessionForUserAndModule(User user, MenuModule menuModule) {
        log.info("Reset previous user session for user: {}, module: {}.", user.getUserName(), menuModule.getCaption());
        resetUserLastSessionToDefault(user, menuModule);

        if(menuModule.equals(MenuModule.DASHBOARD)){
            setDefaultDashboardSessionInGlobalUserSettingsFor(user);
        }
    }

    private static void closeZendeskPopup() {
        getSideBar().getSideBarElm().shouldBe(visible, ENV_CONFIG.pageLoadDuration().multipliedBy(2));
        waitOnWebElmMightAppearsThenClickOnIt(getZendeskPopup().getGotItBtn());
    }
}
