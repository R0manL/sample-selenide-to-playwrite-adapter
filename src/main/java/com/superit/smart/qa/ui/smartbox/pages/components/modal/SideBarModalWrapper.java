package com.superit.smart.qa.ui.smartbox.pages.components.modal;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonGroupWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchAndMultiSelectWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;

@Slf4j
public class SideBarModalWrapper {
    protected final WebElm modalRootElm;
    public SideBarModalWrapper(WebElm rootElm) {
        this.modalRootElm = rootElm;
    }
    private static final String FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE = "//label[normalize-space(.)='%s']/..";
    private static final String MAT_SELECT = "mat-select";


    public WebElm getRoot() {
        return modalRootElm;
    }

    public WebElm getField(@NotNull String caption) {
        return modalRootElm.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public WebElm getField(@NotNull String caption, WebElm parent) {
        return parent.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public WebElm getInput(@NotNull String caption) {
        return getField(caption).$("input");
    }

    public WebElm getInput(@NotNull String caption, WebElm parent) {
        return getField(caption, parent).$("input");
    }

    @NotNull
    public String getInputValue(@NotNull String caption) {
        return getInput(caption).getValue();
    }

    @NotNull
    public String getInputValue(@NotNull String caption, WebElm parent) {
        return getInput(caption, parent).getValue();
    }

    @Nullable
    public Integer getInputValueAsInteger(@NotNull String caption) {
        String value = getInput(caption).getValue();
        return value.isEmpty() ? null : Integer.parseInt(value);
    }

    public WebElm getTextArea(@NotNull String caption) {
        return getField(caption).$("textarea");
    }

    public WebElm getRadioButtonGroup(@NotNull String caption) {
        return modalRootElm.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public RadioButtonGroupWrapper getRadioButtonGroupWrapper(@NotNull String caption) {
        return new RadioButtonGroupWrapper(getField(caption));
    }

    public RadioButtonWrapper getRadioButtonWrapper(@NotNull String label) {
        WebElm radioBtn = $("//mat-radio-button[.//label//*[normalize-space(text())='" + label + "']]");
        return new RadioButtonWrapper(radioBtn);
    }

    public DropdownWrapper getDropdownWrapper(@NotNull String caption) {
        WebElm input = getField(caption).$(MAT_SELECT);
        return new DropdownWrapper(input);
    }

    public DropdownWithSearchWrapper getDropdownWithSearchWrapper(@NotNull String caption) {
        WebElm input = getField(caption).$(MAT_SELECT);
        return new DropdownWithSearchWrapper(input);
    }

    public DropdownWithSearchWrapper getDropdownWithSearchWrapper(@NotNull String caption, WebElm parent) {
        WebElm input = getField(caption, parent).$(MAT_SELECT);
        return new DropdownWithSearchWrapper(input);
    }

    public DropdownWithSearchAndMultiSelectWrapper getDropdownWithSearchAndMultiSelectWrapper(@NotNull String caption) {
        WebElm input = getField(caption).$(MAT_SELECT);
        return new DropdownWithSearchAndMultiSelectWrapper(input);
    }

    public WebElm getSaveBtn() {
        return new ButtonWithTextWrapper(SAVE, modalRootElm).getWebElm();
    }

    public WebElm getButton(ButtonWithIconWrapper.IconClassName value, @NotNull String sectionSelector) {
        return new ButtonWithIconWrapper(value, modalRootElm.$(sectionSelector)).getWebElm();
    }

    public void setDate(@NotNull String value, @NotNull String caption) {
        setInput(value, caption);
    }

    public void setDate(@NotNull LocalDate date, @NotNull String caption) {
        String dateValue = convertDateToStringInFormat(date, DatePattern.UI_DATE_FORMAT);
        setDate(dateValue, caption);
    }

    public void setDate(@NotNull LocalDate date, @NotNull String caption, WebElm parent) {
        String dateValue = convertDateToStringInFormat(date, DatePattern.UI_DATE_FORMAT);
        setDate(dateValue, caption, parent);
    }

    public void setDate(@NotNull String value, @NotNull String caption, WebElm parent) {
        setInput(value, caption, parent);
    }

    public void selectDropdownOption(@NotNull String value, @NotNull String caption) {
        DropdownWithSearchWrapper dropdown = getDropdownWithSearchWrapper(caption).expand();

        if (dropdown.getSearchElm().isDisplayed()) {
            dropdown.searchThenSelect(value);
        } else {
            dropdown.scrollAndSelectFirstByText(value);
        }
    }

    public void selectDropdownOption(@NotNull String value, @NotNull String caption, WebElm parent) {
        DropdownWithSearchWrapper dropdown = getDropdownWithSearchWrapper(caption, parent).expand();

        if (dropdown.getSearchElm().isDisplayed()) {
            dropdown.searchThenSelect(value);
        } else {
            dropdown.scrollAndSelectFirstByText(value);
        }
    }

    public void setInput(@NotNull String value, @NotNull String caption) {
        WebElm input = getInput(caption);
        setInput(value, input);
    }

    public void setInput(int value, @NotNull String caption) {
        setInput(String.valueOf(value), caption);
    }

    public void setInput(@NotNull String value, @NotNull String caption, WebElm parent) {
        WebElm input = getInput(caption, parent);
        setInput(value, input);
    }

    public void setInputIfNotNull(@Nullable String value, @NotNull String caption) {
        if (value != null) {
            setInput(value, caption);
        } else {
            log.debug("Input '{}' = {}. Skip selection.", caption, value);
        }
    }

    public void setInputIfNotNull(@Nullable Integer value, @NotNull String caption) {
        setInputIfNotNull(String.valueOf(value), caption);
    }

    public void setRadioButtonTo(@Nullable Boolean value, @NotNull String caption) {
        WebElm radioButtonField = getRadioButtonGroup(caption);
        new RadioButtonGroupWrapper(radioButtonField).select(value);
    }

    public void setTextArea(@NotNull String value, @NotNull String caption) {
        WebElm textarea = getTextArea(caption);
        setInput(value, textarea);
    }

    private void setInput(@NotNull String value, WebElm input) {
        new InputWrapper(input)
                .clear()
                .sendKeys(value);
    }

    public SideBarModalWrapper selectRadioButton(@NotNull String label) {
        getRadioButtonWrapper(label).select();

        return this;
    }

    public void click(ButtonWithTextWrapper.ButtonText btnText) {
        new ButtonWithTextWrapper(btnText, modalRootElm).click();
    }

    public void click(ButtonWithIconWrapper.IconClassName btnIcon) {
        new ButtonWithIconWrapper(btnIcon, modalRootElm).click();
    }

    public void clickClose() {
        new ButtonWithIconWrapper(CLOSE, modalRootElm).click();
        modalRootElm.should(disappear);
    }
}
