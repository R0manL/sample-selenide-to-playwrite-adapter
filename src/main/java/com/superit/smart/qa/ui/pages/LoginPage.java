package com.superit.smart.qa.ui.pages;

import com.superit.smart.qa.core.enums.User;
import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.sleepForWebElmRefreshDuration;
import static com.superit.smart.qa.utils.WaitUtils.waitOnRoundLoaderMightAppearThenDisappear;


@Slf4j
public class LoginPage {
    private static final WebElm SELECT_ACCOUNT_ELM = $("#otherTile");
    private static final WebElm LOGIN_INPUT = $("input[type='email']:not([aria-hidden='true'])");
    private static final WebElm PASSWORD_INPUT =  $("input[type='password']:not([aria-hidden='true'])");
    private static final WebElm SUBMIT_BTN =  $("input[type='submit']");


    protected void loginAs(@NotNull String email, @NotNull String password) {
        SELECT_ACCOUNT_ELM.clickIfVisible();
        LOGIN_INPUT.sendKeys(email);
        SUBMIT_BTN.click();

        PASSWORD_INPUT.shouldBe(Condition.visible);
        sleepForWebElmRefreshDuration(); //Note. Sometimes pass input has appeared too fast, if we set password it does not work.
        PASSWORD_INPUT.sendKeys(password);
        SUBMIT_BTN.click();

        waitOnRoundLoaderMightAppearThenDisappear();

        if (SUBMIT_BTN.isDisplayed()) {
            SUBMIT_BTN.click();
            waitOnRoundLoaderMightAppearThenDisappear();
        }
    }

    public void loginAs(User user) {
        loginAs(user.getEmail(), user.getPassword());
    }
}