package com.superit.smart.qa.utils;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.ProgressBar;
import com.superit.smart.qa.ui.smartbox.pages.components.RoundLoader;
import com.superit.smart.qa.ui.smartbox.pages.components.Spinner;
import com.superit.smart.qa.ui.core.DelayCondition;
import com.microsoft.playwright.Response;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.messeges.ErrorNotificationMsg.checkErrorNotification;
import static com.superit.smart.qa.ui.core.components.TextPattern.STARTS_FROM_NUMBER;
import static com.superit.smart.qa.utils.NumberUtils.getNumberFromBeginningOf;

/**
 * Created by R0manL.
 */

@Slf4j
public class WaitUtils {
    private static final String TEXT_BEGIN_FROM_WHITESPACES_REGEX = "^\\s*";
    private static final String LOADERS_XPATH = "//*[contains(@class,'sks-content-')]";
    private static final WebElm COLLAB_LOADER = $("collab-app-loader .loader");
    private static final String  WAIT_TILL_DISAPPEAR = "Wait till: '{}' disappear.";

    private WaitUtils() {
        // NONE
    }

    public static void waitOnLoadersDisappearAndCheckErrors() {
        waitOnLoadersDisappearAndCheckErrors(ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnLoadersDisappearAndCheckErrors(Duration duration) {
        log.debug(WAIT_TILL_DISAPPEAR, LOADERS_XPATH);
        waitUntilConditionIsTrueAndCheckErrors($$(LOADERS_XPATH), WebElm::areDisplayed, duration);
    }

    public static void waitOnLoadersDisappearAndCheckErrors(WebElm parentElm) {
        WebElm loaders = parentElm.$$(LOADERS_XPATH);
        log.debug(WAIT_TILL_DISAPPEAR, loaders);
        waitUntilConditionIsTrueAndCheckErrors(loaders, WebElm::areDisplayed, ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnCollabLoaderDisappearAndCheckErrors() {
        log.debug(WAIT_TILL_DISAPPEAR, COLLAB_LOADER);
        waitUntilConditionIsTrueAndCheckErrors(COLLAB_LOADER, WebElm::areDisplayed, ENV_CONFIG.pageLoadDuration());
    }

    public static int waitUntilOwnTextBeginNotFromZeroAndGetNumberValue(WebElm elm) {
        return waitUntilOwnTextBeginFromNumberDifferentFrom(0, elm);
    }

    public static int waitUntilOwnTextIsNotZeroAndGetNumberValue(WebElm elm) {
        return waitUntilOwnTextBeginNotFromZeroAndGetNumberValue(elm);
    }

    public static int waitUntilTextBeginNotFromZero(WebElm elm) {
        return waitUntilTextBeginFromNumberDifferentFrom(0, elm);
    }

    public static int waitUntilOwnTextBeginFromNumberDifferentFrom(int number, WebElm elm) {
        log.info("Wait until element's own text begin from number non equals to: '{}'.", number);

        elm
                .shouldBe(visible)
                .shouldHas(ownText(STARTS_FROM_NUMBER));  // Note. Wait till text has loaded (has any number).

        sleepForWebElmRefreshDuration();

        elm.shouldHas(notOwnText(Pattern.compile(TEXT_BEGIN_FROM_WHITESPACES_REGEX + number)),
                ENV_CONFIG.webElmLoadDuration().multipliedBy(2));

        String updatedValue = elm.getOwnText();
        log.debug("New value is: " + updatedValue);

        return getNumberFromBeginningOf(updatedValue);
    }

    public static int waitUntilTextBeginFromNumberDifferentFrom(int number, WebElm elm) {
        log.info("Wait until element's text begin from number non equals to '{}'.", number);

        elm.shouldBe(visible);
        elm.shouldHas(text(STARTS_FROM_NUMBER));  // Note. Wait till text has loaded (has any number).

        sleepForWebElmRefreshDuration();

        elm.shouldHas(notText(TEXT_BEGIN_FROM_WHITESPACES_REGEX + number),
                ENV_CONFIG.webElmLoadDuration().multipliedBy(2));
        String updatedTextValue = elm.getText();

        log.debug("New value is: " + updatedTextValue);

        return getNumberFromBeginningOf(updatedTextValue);
    }

    public static int waitOnAndGetNumberFromBeginningOf(WebElm webElm) {
        log.debug("Wait till element has text starting from number.");
        String text = webElm.shouldHas(text(STARTS_FROM_NUMBER)).getText();
        return getNumberFromBeginningOf(text);
    }

    public static String waitOnTextMayChange(WebElm elm, @NotNull String origText, Duration duration) {
        log.debug("Wait on text: '{}' will change.", origText);
        waitUntilConditionIsTrueAndCheckErrors(elm, element -> origText.equals(element.getText()), duration);
        return elm.getText();
    }

    public static String waitOnValueMayChange(WebElm input, Duration duration) {
        final String origValue = input.getValue();
        log.debug("Wait on value: '{}' will change.", origValue);
        waitUntilConditionIsTrueAndCheckErrors(input, element -> origValue.equals(element.getValue()), duration);
        return input.getValue();
    }

    public static void waitOnWebElmsMightAppearThenDisappear(WebElm elm, Duration appearDuration, Duration disappearDuration) {
        log.debug("Wait on '{}' might appear then disappear.", elm);
        waitUntilConditionIsTrueAndCheckErrors(elm, WebElm::areHidden, appearDuration);
        waitUntilConditionIsTrueAndCheckErrors(elm, WebElm::areDisplayed, disappearDuration);
    }

    public static void waitOnWebElmMightAppearThenDisappear(WebElm elm, Duration appearDuration) {
        waitOnWebElmMightAppearThenDisappear(elm, appearDuration, ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnWebElmMightAppearThenDisappear(WebElm elm, Duration appearDuration, Duration disappearDuration) {
        log.debug("Wait on '{}' might appear then disappear.", elm);
        waitUntilConditionIsTrueAndCheckErrors(elm, WebElm::areDisplayed, appearDuration);
        elm.get(0).shouldBe(hidden, disappearDuration); // Note. Sometimes in DOM we have 2 round loaders.
        sleepForWebElmRefreshDuration();
    }

    public static void waitOnWebElmMightAppearsThenClickOnIt(WebElm button) {
        waitOnWebElmMightAppearsThenClickOnIt(button, ENV_CONFIG.webElmLoadDuration());
    }

    public static void waitOnWebElmMightAppearsThenClickOnIt(WebElm button, Duration duration) {
        log.debug("Wait on '{}' might appear then click on it.", button);
        waitUntilConditionIsTrueAndCheckErrors(button, WebElm::isHidden, duration);

        if (button.isDisplayed()) {
            log.debug("Element has appear clicking on it: {}.", button.getLocator().toString());
            button.click();
        } else {
            log.warn("Element has not appeared: {}", button.getLocator().toString());
        }
    }

    public static void waitOnWebElmAppearsAndDisappear(WebElm elm) {
        waitOnWebElmAppearsAndDisappear(elm, ENV_CONFIG.webElmLoadDuration(), ENV_CONFIG.webElmLoadDuration());
    }

    public static void waitOnWebElmAppearsAndDisappear(WebElm elm, Duration appearDuration, Duration disappearDuration) {
        log.info("Wait for elm: {} to be displayed then disappear.", elm.getLocator());

        elm
                .should(appear, appearDuration)
                .should(disappear, disappearDuration);
    }

    public static void waitOnSpinnerDisappear() {
        waitOnSpinnerDisappear(ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnSpinnerDisappear(Duration timeout) {
        waitOnSpinnerDisappear(null, timeout);
    }

    public static void waitOnSpinnerDisappear(WebElm parentElm) {
        waitOnSpinnerDisappear(parentElm, ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnSpinnerDisappear(@Nullable WebElm parentElm, Duration timeout) {
        WebElm spinner = new Spinner().getRoundProgressSpinnerElm(parentElm);
        waitOnWebElmMightAppearThenDisappear(spinner, ENV_CONFIG.webElmRefreshDuration(), timeout);
    }

    public static void waitOnProgressBarMightAppearThenDisappear(WebElm parentElm) {
        WebElm progressBar = new ProgressBar().getProgressBar(parentElm);

        waitOnWebElmMightAppearThenDisappear(progressBar,
                ENV_CONFIG.webElmRefreshDuration().multipliedBy(8),
                ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnRoundProgressSpinnerMightAppearThenDisappear(WebElm parentElm) {
        WebElm spinner = new Spinner().getRoundProgressSpinnerElm(parentElm);

        waitOnWebElmMightAppearThenDisappear(spinner,
                ENV_CONFIG.webElmRefreshDuration().multipliedBy(8),
                ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnRoundLoaderMightAppearThenDisappear() {
        WebElm loader = new RoundLoader().getRoundLoaderElm(null);

        waitOnWebElmMightAppearThenDisappear(loader,
                ENV_CONFIG.webElmRefreshDuration().multipliedBy(8),
                ENV_CONFIG.pageLoadDuration());
    }

    public static void waitOnRoundLoaderMightAppearThenDisappear(WebElm parentElm) {
        WebElm loader = new RoundLoader().getRoundLoaderElm(parentElm);

        waitOnWebElmMightAppearThenDisappear(loader,
                ENV_CONFIG.webElmRefreshDuration().multipliedBy(8),
                ENV_CONFIG.pageLoadDuration());
    }

    public static WebElm waitAndReturnFirstVisibleElmOf(WebElm webElm1, WebElm webElm2) {
        waitOnVisibilityAnyOf(webElm1, webElm2);

        if (webElm1.isDisplayed() && webElm2.isDisplayed()) {
            log.warn("Both elements are displayed, return first one.");
        }

        return webElm1.isDisplayed() ? webElm1 : webElm2;
    }

    public static void waitOnVisibilityAnyOf(WebElm webElm1, WebElm webElm2) {
        webElm1.shouldBe(visibleOr(webElm2), ENV_CONFIG.webElmLoadDuration().multipliedBy(5));
    }

    public static void clickAndWaitOnResponseUrlContains(WebElm button, @NotNull String value) {
        log.debug("Click at: '{}' and wait on response url contains: {}", button.getLocator(), value);
        Predicate<Response> widgetDefaultValuesResp = response -> response.url().contains(value) && response.ok();
        button.clickAndWaitOnResponse(widgetDefaultValuesResp);
    }

    /**
     * Wait until widget (e.g. create option, terminal rights) default values will not be loaded.
     *
     * @param button clicking at - initiate request.
     */
    public static void clickAndWaitOnWidgetDefaultValuesResponse(WebElm button) {
        clickAndWaitOnResponseUrlContains(button, "widgetdefaultvalues");
    }

    private static <T> boolean waitUntilConditionIsTrueAndCheckErrors(T element, DelayCondition<T> condition, Duration duration) {
        final long WAIT_TIME_FOR_ITERATION = ENV_CONFIG.webElmRefreshDuration().multipliedBy(2).toMillis();
        final long MAX_ATTEMPTS = duration.toMillis() / WAIT_TIME_FOR_ITERATION;
        long iteration = 0;

        do {
            log.debug("Wait on condition and check error msgs. Iteration: {} ({}).", iteration, MAX_ATTEMPTS);
            sleep(WAIT_TIME_FOR_ITERATION);
            checkErrorNotification();
            iteration++;
        } while (condition.check(element) && (iteration < MAX_ATTEMPTS));

        return condition.check(element);
    }
}
