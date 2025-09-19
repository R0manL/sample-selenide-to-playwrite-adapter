package com.superit.smart.qa.ui.core.components.dropdown;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.list.ListOfElmsWithScroller;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.sleepForWebElmRefreshDuration;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;


@Slf4j
public class DropdownWrapper {
    protected static final WebElm DROPDOWN = $("[class*='select-panel'][role='listbox']");
    protected static final WebElm OPTION_ELMS = DROPDOWN.$$("[role='option']:not(.is-hidden)");
    protected static final WebElm OPTION_TITLE_ELMS = DROPDOWN.$$(".mat-option-text .value-name");
    protected final WebElm input;


    /**
     * Dropdown list constructor.
     *
     * @param input - Element that should be clicked to expand dropdown list.
     */
    public DropdownWrapper(WebElm input) {
        this.input = input;
    }

    public WebElm getSelectedOption() {
        return this.input;
    }

    @NotNull
    public String getSelectedOptionText() {
        return getSelectedOption().getText();
    }

    public WebElm getVisibleOptions() {
        return OPTION_ELMS;
    }

    //ToDo test handling of not visible elements.
    public WebElm getTitlesOfVisibleOptions() {
        return OPTION_TITLE_ELMS;
    }

    public DropdownWrapper expand() {
        this.input.shouldBe(visible); // Wait till dropdown will load and be ready for interaction.
        waitOnLoadersDisappearAndCheckErrors(); //If we try to expand dropdown when page is still loading, page refresh and dropdown collapse.
        sleepForWebElmRefreshDuration(2); // Note. Sometimes expand does not work properly without small delay.
        this.input.click();

        // Sometimes dropdown has not expanded after clicking. Try to click second time.
        sleepForWebElmRefreshDuration();
        if(!DROPDOWN.isDisplayed()) {
            log.warn("Dropdown has not expanded from the firsts attempt, try again.");
            this.input.click();
        }

        DROPDOWN.shouldBe(visible); // Wait till dropdown will open and fully loaded.
        sleepForWebElmRefreshDuration(); // Note. Sometimes expand does not work properly without small delay.

        return this;
    }

    public void selectBy(@NotNull String text) {
        OPTION_ELMS.getByText(text).click();
    }

    public void selectByExact(@NotNull String text) {
        OPTION_ELMS.getByExactText(text).click();
    }

    public DropdownWrapper scrollAndSelectFirstByText(@NotNull String text) {
        new ListOfElmsWithScroller(OPTION_ELMS)
                .findFirstChildBy(text)
                .click();

        DROPDOWN.shouldBe(hidden);

        return this;
    }

    public void scrollAndSelectFirstByExactText(@NotNull String text) {
        new ListOfElmsWithScroller(OPTION_ELMS)
                .findFirstChildByExactText(text)
                .click();
    }

    public DropdownWrapper expandAndSelectIfNotSelectedBy(@NotNull String text) {
        if (getSelectedOptionText().equals(text)) {
            log.warn("Dropdown option: '{}' has already selected. Skip selection.", text);
        } else {
            expand().scrollAndSelectFirstByText(text);
        }

        return this;
    }

    public void collapse() {
        DROPDOWN.pressEscape();
        DROPDOWN.should(disappear);
    }

    public void removeSelectedOption() {
        this.input.shouldBe(visible);

        ButtonWithIconWrapper xButton = new ButtonWithIconWrapper(CLOSE, this.input.parent());
        if (xButton.getWebElm().isDisplayed()) {
            xButton.click();
        } else {
            log.warn("X button is not displayed for the dropdown");
        }
    }
}
