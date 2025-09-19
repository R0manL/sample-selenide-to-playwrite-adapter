package com.superit.smart.qa.ui.smartbox.pages.components.checkbox;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Represent 'mat-checkbox' UI component.
 */

@Slf4j
public class CheckboxMatWrapper {
    private static final String CHECKED_CSS_CLASS = "mat-checkbox-checked";
    private final WebElm checkbox;

    public CheckboxMatWrapper(WebElm parent) {
        this.checkbox = parent.$("mat-checkbox");
    }

    public CheckboxMatWrapper(@NotNull String caption, WebElm parent) {
        this.checkbox = parent.$("//mat-checkbox[.//*[normalize-space(text())=\"" + caption + "\"]]");
    }

    public WebElm getCheckbox() {
        return this.checkbox;
    }

    public void select(boolean value) {
        if (this.isChecked() ^ value) {
            this.checkbox.$(".mat-checkbox-inner-container").click();

            if (value) {
                checkbox.should(Condition.cssClassContains(CHECKED_CSS_CLASS));
            } else {
                checkbox.should(Condition.notCssClassContains(CHECKED_CSS_CLASS));
            }
        } else {
            log.warn("Checkbox has already state = " + value + ". Skip.");
        }
    }

    public boolean isChecked() {
        return isChecked(this.checkbox);
    }

    protected boolean isChecked(WebElm checkbox) {
        return checkbox.containsCssClass(CHECKED_CSS_CLASS);
    }
}
