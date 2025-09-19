package com.superit.smart.qa.ui.smartbox.pages.components.widget;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.field.FieldWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.EDIT;

@Slf4j
public class BoxWidgetWrapper extends WidgetWrapper {
    private final FieldWrapper fieldWrapper;


    public BoxWidgetWrapper(WidgetID value) {
        super(value);
        this.fieldWrapper = new FieldWrapper(widgetRootElm);
    }

    public WebElm getField(@NotNull String caption) {
        return fieldWrapper.getField(caption);
    }

    public WebElm getFieldLink(@NotNull String caption) {
        return fieldWrapper.getFieldLink(caption);
    }

    @NotNull
    public String getFieldLinkValue(@NotNull String caption) {
        return fieldWrapper.getFieldLinkValue(caption);
    }

    public WebElm getFieldValueElm(@NotNull String caption) {
        return fieldWrapper.getFieldValueElm(caption);
    }

    @NotNull
    public String getFieldValue(@NotNull String caption) {
        return fieldWrapper.getFieldValue(caption);
    }

    @Nullable
    public Boolean getFieldValueAsBoolean(@NotNull String caption) {
        return fieldWrapper.getFieldValueAsBoolean(caption);
    }

    public void clickEdit() {
        new ButtonWithIconWrapper(EDIT, widgetRootElm).click();
    }
}
