package com.superit.smart.qa.ui.smartbox.pages.components.radiobutton;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonGroupWrapper.State.*;


@Slf4j
public class RadioButtonGroupWrapper {
    private final WebElm parent;
    private final WebElm yesRadioBtn;
    private final WebElm yesSelectedRadioBtn;
    private final WebElm noRadioBtn;
    private final WebElm noSelectedRadioBtn;

    public RadioButtonGroupWrapper(WebElm parent) {
        this.parent = parent;
        this.yesRadioBtn = parent.$("//mat-radio-button[.//input[@value='true']]");
        this.yesSelectedRadioBtn = parent.$("mat-radio-button.mat-radio-checked input[value='true']");
        this.noRadioBtn = parent.$("//mat-radio-button[.//input[@value='false']]");
        this.noSelectedRadioBtn = parent.$("mat-radio-button.mat-radio-checked input[value='false']");
    }

    /**
     * Getting selected value when radiobutton group present as radio buttons elements.
     * @return selected state
     */
    @Nullable
    public Boolean getSelectedValueAsBoolean() {
        if (isYesSelected()) { return true;}
        if (isNoSelected()) { return false;}

        return null;
    }

    /**
     * Getting selected value when radipbutton group present as simple text with Yes/No/No data text.
     * @return
     */
    @Nullable
    public Boolean getTextAsBoolean() {
        String value = parent.$(".widget-field-value").shouldBe(Condition.notEmpty).getText();

        if (YES.toString().equals(value)) return true;
        if (NO.toString().equals(value)) return false;
        if (NO_DATA.toString().equals(value)) return null;

        throw new IllegalStateException("Can't identify state if radiobutton: " + parent.getLocator().toString());
    }

    public void selectYes() {
        log.debug("Select 'Yes' for radiobutton: '{}'", parent);
        yesRadioBtn.click();
    }

    public void selectNo() {
        log.debug("Select 'No' for radiobutton: '{}'", parent);
        noRadioBtn.click();
    }

    public void select(@Nullable Boolean value) {
        if (value == null) {
            clearSelection();
            return;
        }

        if (value) {
            selectYes();
        } else {
            selectNo();
        }
    }

    public void clearSelection() {
        parent.$("//button").click();
    }

    public boolean isYesSelected() {
        log.debug("Check if 'Yes' selected for radiobutton: '{}'", parent);
        return yesSelectedRadioBtn.isDisplayed();
    }

    public boolean isNoSelected() {
        log.debug("Check if 'No' selected for radiobutton: '{}'", parent);
        return noSelectedRadioBtn.isDisplayed();
    }

    public WebElm getSelectedYesRadioBtn() {
        return yesSelectedRadioBtn;
    }

    public WebElm getSelectedNoRadioBtn() {
        return noSelectedRadioBtn;
    }

    public WebElm getSelectedYesOrNoRadioBtnBy(boolean value) {
        return value ? yesSelectedRadioBtn : noSelectedRadioBtn;
    }

    public enum State {
        YES("Yes"),
        NO_DATA("No data"),
        NO("No");

        private final String text;

        State(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }
}