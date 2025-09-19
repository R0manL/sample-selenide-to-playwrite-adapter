package com.superit.smart.qa.ui.smartbox.pages.components.modal;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonGroupWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.core.playwright.conditions.Condition.notEmpty;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static com.superit.smart.qa.utils.DateUtils.convertStringToLocalDateInFormat;

@Slf4j
public class ModalWrapper {
    protected final WebElm modalRootElm;
    protected final WebElm modalTitle;
    private static final String FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE = "//*[contains(@class, 'widget-field-title') " +
            "and normalize-space(.)='%s']/..";
    private static final String INPUT = "input";
    private static final String MAT_SELECT = "mat-select";
    private static final String SKIP_SELECTION_TEMPLATE = "'{}' = {}. Skip selection";


    public ModalWrapper(WebElm rootElm) {
        this.modalRootElm = rootElm;
        this.modalTitle = rootElm.$("[mat-dialog-title]");
    }

    public WebElm getRootElm() {
        return modalRootElm;
    }

    public WebElm getField(@NotNull String caption) {
        return modalRootElm.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public WebElm getField(@NotNull String caption, WebElm parent) {
        return parent.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public  WebElm getFieldErrorMsg(@NotNull String caption) {
        return getField(caption).$(".error-messages");
    }

    public WebElm getInput(@NotNull String caption) {
        return getField(caption).$(INPUT);
    }

    public WebElm getInput(@NotNull String caption, WebElm parent) {
        return getField(caption, parent).$(INPUT);
    }

    public InputWrapper getInputWrapper(@NotNull String caption) {
        return new InputWrapper(getInput(caption));
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

    @Nullable
    public Float getInputValueAsFloat(@NotNull String caption) {
        String value = getInput(caption).getValue();
        return value.isEmpty() ? null : Float.parseFloat(value);
    }

    @NotNull
    public LocalDate getInputDate(@NotNull String caption) {
        String dateAsText = getInput(caption).shouldBe(notEmpty).getValue();
        return convertStringToLocalDateInFormat(dateAsText, DatePattern.UI_DATE_FORMAT);
    }

    @NotNull
    public LocalDate getInputDate(@NotNull String caption, WebElm parent) {
        String dateAsText = getInput(caption, parent).shouldBe(notEmpty).getValue();
        return convertStringToLocalDateInFormat(dateAsText, DatePattern.UI_DATE_FORMAT);
    }

    @Nullable
    public LocalDate getInputDateNullable(@NotNull String caption) {
        String dateAsText = getInputValue(caption);
        return dateAsText.isEmpty() ? null : convertStringToLocalDateInFormat(dateAsText, DatePattern.UI_DATE_FORMAT);
    }

    public WebElm getTextArea(@NotNull String caption) {
        return getField(caption).$("textarea");
    }

    public RadioButtonWrapper getRadioButtonWrapper(@NotNull String label) {
        WebElm radioBtn = modalRootElm.$("//mat-radio-button[.//*[normalize-space(text())='" + label + "']]");
        return new RadioButtonWrapper(radioBtn);
    }

    public WebElm getRadioButtonGroup(@NotNull String caption) {
        return modalRootElm.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    public RadioButtonGroupWrapper getRadioButtonGroupWrapper(@NotNull String caption) {
        return new RadioButtonGroupWrapper(getField(caption));
    }

    public Boolean getRadioButtonGroupValue(@NotNull String caption) {
        return getRadioButtonGroupWrapper(caption).getSelectedValueAsBoolean();
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

    public ListModalWrapper getListModalWrapper(@NotNull String caption) {
        WebElm selectElm = getField(caption).$(INPUT);
        return new ListModalWrapper(selectElm);
    }

    public WebElm getDropdownReadOnly(@NotNull String caption) {
        return getField(caption).$("control-it-dropdown mat-form-field.readonly");
    }

    @NotNull
    public String getDropdownSelectedOption(@NotNull String caption) {
        return getDropdownWithSearchWrapper(caption).getSelectedOptionText();
    }

    @NotNull
    public String getDropdownSelectedOption(@NotNull String caption, WebElm parent) {
        return getDropdownWithSearchWrapper(caption, parent).getSelectedOptionText();
    }

    public WebElm getErrorMsgFor(@NotNull String caption) {
        return getField(caption).$(".error-messages");
    }

    public void setDate(@NotNull String value, @NotNull String caption) {
        setInput(value, caption);
    }

    public void setDate(@NotNull LocalDate date, @NotNull String caption) {
        String dateValue = convertDateToStringInFormat(date, DatePattern.UI_DATE_FORMAT);
        setDate(dateValue, caption);
    }

    public void setDateIfNotNull(@Nullable LocalDate value, @NotNull String caption) {
        if (value != null) {
            setDate(value, caption);
        } else {
            log.debug(SKIP_SELECTION_TEMPLATE, caption, value);
        }
    }

    public void setDateIfNotNull(@Nullable String value, @NotNull String caption) {
        if (value != null) {
            setDate(value, caption);
        } else {
            log.debug(SKIP_SELECTION_TEMPLATE, caption, value);
        }
    }

    public void setDate(@NotNull LocalDate date, @NotNull String caption, WebElm parent) {
        String dateValue = convertDateToStringInFormat(date, DatePattern.UI_DATE_FORMAT);
        setDate(dateValue, caption, parent);
    }

    public void setDate(@NotNull String value, @NotNull String caption, WebElm parent) {
        setInput(value, caption, parent);
    }

    public void setDateAndPressTab(@NotNull LocalDate value, @NotNull String caption) {
        setDate(value, caption);
        modalRootElm.pressTab();
    }

    public void selectDropdownOption(@NotNull String value, @NotNull String caption) {
        DropdownWithSearchWrapper dropdown = getDropdownWithSearchWrapper(caption)
                .expand();

        if (dropdown.getSearchElm().isDisplayed()) {
            dropdown.searchThenSelect(value);
        } else {
            dropdown.scrollAndSelectFirstByText(value);
        }
    }

    public void selectDropdownOptionIfNotNull(@Nullable String value, @NotNull String caption) {
        if (value != null) {
            selectDropdownOption(value, caption);
        } else {
            log.debug(SKIP_SELECTION_TEMPLATE, caption, value);
        }
    }

    public void selectListModalOptionStartWithIfNotNull(@Nullable String id, @NotNull String caption, ListModalWrapper.ColID colId) {
        if (id != null) {
            WebElm selectElm = getField(caption).$(INPUT);
            new ListModalWrapper(selectElm).expandSearchAndSelectByStartWith(id, colId);
        } else {
            log.warn("ID is null. Skip selection.");
        }
    }

    public void selectListModalOptionByExactTextIfNotNull(@Nullable String id, @NotNull String caption, ListModalWrapper.ColID colId) {
        if (id != null) {
            WebElm selectElm = getField(caption).$(INPUT);
            new ListModalWrapper(selectElm).expandAndSelectByExactText(id, colId);
        } else {
            log.warn("ID is null. Skip selection.");
        }
    }

    public void selectListModalOptionWithAdditionalFiltering(@NotNull String startWithText,
                                                             @NotNull String caption,
                                                             ListModalWrapper.ColID selectColId,
                                                             @NotNull String additionalFilteringText,
                                                             ListModalWrapper.ColID filterColId) {
        WebElm selectElm = getField(caption).$(INPUT);
        new ListModalWrapper(selectElm)
                .expand()
                .searchByTwoColumnsAndSelectByFirstColTextStartWith(
                        startWithText,
                        selectColId,
                        additionalFilteringText,
                        filterColId);
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

    public void setInput(float value, @NotNull String caption) {
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

    public void setDateAndPressTab(@Nullable String value, @NotNull String caption) {
        setInputIfNotNull(value, caption);
        modalRootElm.pressTab();
    }

    public void setInputIfNotNull(@Nullable Integer value, @NotNull String caption) {
        if (value != null) {
            setInput(String.valueOf(value), caption);
        } else {
            log.debug("'{}' = {}. Skip selection.", caption, value);
        }
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

    public void setTextAreaIfNotNull(@Nullable String value, @NotNull String caption) {
        if (value != null) {
            setTextArea(value, caption);
        } else {
            log.debug("'{}' = {}. Skip selection.", caption, value);
        }
    }

    public void removeInputValue(@NotNull String caption) {
        InputWrapper input = getInputWrapper(caption);
        input.clear();
    }

    public void click(ButtonWithTextWrapper.ButtonText btnText) {
        new ButtonWithTextWrapper(btnText, modalRootElm).click();
    }

    public void click(ButtonWithIconWrapper.IconClassName btnIcon) {
        new ButtonWithIconWrapper(btnIcon, modalRootElm).click();
    }

    public WebElm getSaveBtn() {
        return new ButtonWithTextWrapper(SAVE, modalRootElm).getWebElm();
    }

    public void clickSave() {
        getSaveBtn().click();
    }

    public void clickClose() {
        new ButtonWithIconWrapper(CLOSE, modalTitle).click();
        modalRootElm.should(disappear);
    }

    public void clickClose(@NotNull String parentSelector) {
        new ButtonWithIconWrapper(CLOSE, modalRootElm.$(parentSelector)).click();
        modalRootElm.should(disappear);
    }
}
