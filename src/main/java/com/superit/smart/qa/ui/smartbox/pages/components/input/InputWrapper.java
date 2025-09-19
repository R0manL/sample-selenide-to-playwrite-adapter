package com.superit.smart.qa.ui.smartbox.pages.components.input;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.conditions.Condition.empty;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static com.superit.smart.qa.utils.WaitUtils.waitOnValueMayChange;

/**
 * Created by R0manL.
 */

@Slf4j
public class InputWrapper {
    protected final WebElm field;
    @Getter
    protected final WebElm input;


    public InputWrapper(WebElm input) {
        this.field = input.$("//ancestor::mat-form-field");
        this.input = input;
    }

    public void pressTab() {
        this.input.pressTab();
    }

    public void pressEnter() {
        this.input.pressEnter();
    }

    public InputWrapper sendKeys(@NotNull String value) {
        this.input.sendKeys(value);

        return this;
    }

    public InputWrapper setValue(@NotNull String value) {
        log.info("Set input value '{}'.", value);
        this.input.val(value);

        return this;
    }

    public InputWrapper setDate(@NotNull LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        sendKeys(dateValue);
        return this;
    }

    /**
     * Clear input's value.
     * @return input wrapper
     */

    public InputWrapper clear() {
        input.shouldBe(visible);
        waitOnValueMayChange(input, ENV_CONFIG.webElmRefreshDuration().multipliedBy(2));

        if (!input.getValue().isEmpty()) {
            WebElm removeBtn = new ButtonWithIconWrapper(CLOSE, this.field).getWebElm();

            if (removeBtn.isDisplayed()) {
                removeBtn.click();
            } else {
                clearByEmptyString();
                //clearWithBackspace(); //ToDo for some tests clicking END scroll to the end of page instead of end of input text.
                // Remove with filing input with ''. If this solution will work well, remove commented lines.
            }
        } else {
            log.debug("Input is empty. Skip clearing.");
        }

        input.shouldBe(empty);

        return this;
    }

    public void click() {
        this.input.click();
    }

    public InputWrapper clearWithBackspace() {
        String value = input.getValue();

        if (!value.isEmpty()) {
            input.pressEnd();
            input.pressEnd(); //Note. If 1st input.pressEnd() initiate scroll into element, cursor has not placed at the end. Second call required.

            for (int i = 0; i < value.length(); i++) {
                input.pressBackspace();
            }
        } else {
            log.warn("Value has already empty. Skip clear.");
        }
        return this;
    }

    private void clearByEmptyString() {
        input.getLocator().fill("");
    }
}
