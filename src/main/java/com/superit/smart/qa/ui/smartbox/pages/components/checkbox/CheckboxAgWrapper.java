package com.superit.smart.qa.ui.smartbox.pages.components.checkbox;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;


/**
 *  Represent 'ag-checkbox' UI component.
 */

@Slf4j
public class CheckboxAgWrapper {
    private final WebElm checkbox;
    private static final String CHECKED_CSS_CLASS_NAME = "ag-checked";

    public CheckboxAgWrapper(WebElm parent) {
        this.checkbox = parent.$(".ag-checkbox-input-wrapper");
    }

    public void select(boolean value) {
        if (this.isChecked() ^ value) {
            this.checkbox.click();

            if (value) {
                checkbox.should(Condition.cssClassContains(CHECKED_CSS_CLASS_NAME));
            } else {
                checkbox.should(Condition.notCssClassContains(CHECKED_CSS_CLASS_NAME));
            }
        } else {
            log.warn("Checkbox has already state = " + value + ". Skip.");
        }
    }

    private boolean isChecked() {
        return this.checkbox.containsCssClass(CHECKED_CSS_CLASS_NAME);
    }
}
