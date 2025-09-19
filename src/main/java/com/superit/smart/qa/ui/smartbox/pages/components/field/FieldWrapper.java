package com.superit.smart.qa.ui.smartbox.pages.components.field;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonGroupWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

import static com.superit.smart.qa.core.playwright.conditions.Condition.notEmpty;
import static com.superit.smart.qa.ui.smartbox.enums.EmptyFieldText.NO_DATA;
import static com.superit.smart.qa.utils.DateUtils.convertStringToLocalDateInFormat;

public class FieldWrapper {
    private final WebElm parent;

    public FieldWrapper(WebElm parent) {
        this.parent = parent;
    }


    public WebElm getField(@NotNull String caption) {
        return parent.$("//*[contains(@class,'field-item') and .//*[normalize-space(text())='" + caption + "']]");
    }

    public WebElm getFieldLink(@NotNull String caption) {
        return getField(caption).$("a");
    }

    @NotNull
    public String getFieldLinkValue(@NotNull String caption) {
        return getFieldLink(caption).getText();
    }

    public WebElm getFieldValueElm(@NotNull String caption) {
        return getField(caption).$(".widget-field-value");
    }

    @NotNull
    public String getFieldValue(@NotNull String caption) {
        return getFieldValueElm(caption).getText();
    }

    @NotNull
    public LocalDate getFieldValueAsDate(@NotNull String caption) {
        String dateAsText = getFieldValueElm(caption).shouldBe(notEmpty).getText();
        return convertStringToLocalDateInFormat(dateAsText, DatePattern.UI_DATE_FORMAT);
    }

    @Nullable
    public LocalDate getFieldValueAsDateNullable(@NotNull String caption) {
        String dateAsText = getFieldValue(caption);

        return dateAsText.isEmpty() || dateAsText.equals(NO_DATA.getText())
                ? null
                : convertStringToLocalDateInFormat(dateAsText, DatePattern.UI_DATE_FORMAT);
    }

    @Nullable
    public Boolean getFieldValueAsBoolean(@NotNull String caption) {
        return new RadioButtonGroupWrapper(getField(caption)).getTextAsBoolean();
    }
}
