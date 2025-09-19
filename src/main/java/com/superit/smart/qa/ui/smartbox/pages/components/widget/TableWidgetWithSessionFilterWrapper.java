package com.superit.smart.qa.ui.smartbox.pages.components.widget;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.field.FieldWrapper;
import com.superit.smart.qa.ui.smartbox.pojo.IndexValueUI;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


@Slf4j
public class TableWidgetWithSessionFilterWrapper extends TableWidgetWrapper {
    private final FieldWrapper fieldWrapper;
    private final WebElm filterRootElm = widgetRootElm.$(".widget-left-content");

    public TableWidgetWithSessionFilterWrapper(WidgetID value) {
        super(value);
        this.fieldWrapper = new FieldWrapper(filterRootElm);
    }

    public TableWidgetWithSessionFilterWrapper resetAllFilters() {
        new ButtonWithTextWrapper(ButtonWithTextWrapper.ButtonText.RESET_ALL_FILTERS, filterRootElm).click();
        return this;
    }

    public DropdownWrapper getDropdown(DropdownCaption value) {
        WebElm input = fieldWrapper.getField(value.toString()).$("mat-select");
        return new DropdownWrapper(input);
    }

    public void remove(IndexValueUI indexValueUI) {
        clickEdit();
        remove(indexValueUI.getValues(), 1);
    }

    // Filter field
    public enum DropdownCaption {
        SESSION("Session:"),
        ASSET("Asset:");

        private final String text;

        DropdownCaption(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
