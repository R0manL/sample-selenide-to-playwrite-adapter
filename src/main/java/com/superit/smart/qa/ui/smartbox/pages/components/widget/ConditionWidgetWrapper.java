package com.superit.smart.qa.ui.smartbox.pages.components.widget;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.field.FieldWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ConditionsEditableModalWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ConfirmModalWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

import static com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder.SEARCH;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.*;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class ConditionWidgetWrapper extends WidgetWrapper {
    private final FieldWrapper fieldwrapper;
    private final WebElm leftSideRootElm = widgetRootElm.$(".left-side");
    private final WebElm formRootElm = widgetRootElm.$("form");


    public ConditionWidgetWrapper(WidgetID value) {
        super(value);
        this.fieldwrapper = new FieldWrapper(formRootElm);
    }

    public WebElm getDeleteBtn() {
        return new ButtonWithIconWrapper(DELETE, widgetRootElm).getWebElm();
    }

    public ConfirmModalWrapper clickDelete() {
        getDeleteBtn().click();
        return new ConfirmModalWrapper();
    }

    public void selectTab(@NotNull String text) {
        formRootElm.$(".tab-links").$("//a[normalize-space(text())='" + text + "']").click();
    }

    public WebElm getField(@NotNull String caption) {
        return fieldwrapper.getField(caption);
    }

    public WebElm getFieldValueElm(@NotNull String caption) {
        return fieldwrapper.getFieldValueElm(caption);
    }

    @NotNull
    public String getFieldValue(@NotNull String caption) {
        return fieldwrapper.getFieldValue(caption);
    }

    @NotNull
    public LocalDate getFieldValueAsDate(@NotNull String caption) {
        return fieldwrapper.getFieldValueAsDate(caption);
    }

    @Nullable
    public LocalDate getFieldValueAsDateNullable(@NotNull String caption) {
        return fieldwrapper.getFieldValueAsDateNullable(caption);
    }

    @Nullable
    public Boolean getFieldValueAsBoolean(@NotNull String caption) {
        return fieldwrapper.getFieldValueAsBoolean(caption);
    }

    public ItemWrapper getItemWrapperBy(LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        return new ItemWrapper(List.of(dateValue));
    }

    public void selectItemBy(@NotNull String value) {
        new ItemWrapper(List.of(value)).select();
    }

    public void selectItemBy(LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        new ItemWrapper(List.of(dateValue)).select();
    }

    public void searchItemBy(@NotNull String text) {
        new InputWithPlaceholderWrapper(SEARCH, leftSideRootElm).search(text);
    }

    public void searchItemBy(LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        new InputWithPlaceholderWrapper(SEARCH, leftSideRootElm).search(dateValue);
    }

    public void deleteItemIfFindBy(List<String> values, int uniqueValueIndex) {
        waitOnLoadersDisappearAndCheckErrors(widgetRootElm);

        searchItemBy(values.get(uniqueValueIndex));
        waitOnLoadersDisappearAndCheckErrors();

        ItemWrapper itemWrapper = new ItemWrapper(values);
        if (itemWrapper.isDisplayed()) {
            itemWrapper.select();
            clickDelete()
                    .clickYesDelete();
        } else {
            log.debug("No item has been found with values: " + values);
        }
    }

    public void deleteItemIfFindBy(LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        deleteItemIfFindBy(List.of(dateValue), 0);
    }

    public void deleteItemBy(List<String> values, int uniqueValueIndex) {
        waitOnLoadersDisappearAndCheckErrors(widgetRootElm);

        searchItemBy(values.get(uniqueValueIndex));

        new ItemWrapper(values)
                .select()
                .clickDelete()
                .clickYesDelete();
    }

    public void deleteItemBy(LocalDate value) {
        String dateValue = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        deleteItemBy(List.of(dateValue), 0);
    }

    public ConditionsEditableModalWrapper clickAdd() {
        new ButtonWithIconWrapper(ADD, widgetRootElm).click();
        return new ConditionsEditableModalWrapper();
    }

    public void clickEdit() {
        new ButtonWithIconWrapper(EDIT, formRootElm).click();
    }

    public ConfirmModalWrapper clickCopy() {
        new ButtonWithIconWrapper(COPY, formRootElm).click();
        return new ConfirmModalWrapper();
    }


    public class ItemWrapper {
        private final WebElm itemElm;

        public ItemWrapper(List<String> values) {
            StringBuilder xpath = new StringBuilder("//*[contains(@class, 'left-side-item') and not(contains(@class, 'left-side-item-row'))");
            for (String value : values) {
                xpath.append(" and .//*[@class='field value' and .//*[normalize-space(text())='").append(value).append("']");
            }

            xpath.append("]]");

            itemElm = leftSideRootElm.$(xpath.toString());
        }

        public WebElm getItem() {
            return itemElm;
        }

        public boolean isDisplayed() {
            return itemElm.isDisplayed();
        }

        public ConditionWidgetWrapper select() {
            itemElm.click();

            return ConditionWidgetWrapper.this;
        }
    }
}
