package com.superit.smart.qa.ui.smartbox.pages.components.radiobutton;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.conditions.Condition.cssClassContains;


@Slf4j
public class RadioButtonWrapper {
    private final WebElm radiobutton;
    private static final String CHECKED_RADIOBUTTON_CSS_CLASS_NAME = "mat-radio-checked";


    public RadioButtonWrapper(WebElm radiobutton) {
        this.radiobutton = radiobutton;
    }

    public RadioButtonWrapper(@NotNull String caption, WebElm parent) {
        this.radiobutton = parent.$("//mat-radio-button[.//*[normalize-space(text())='" + caption + "']]");
    }

    public boolean hasChecked() {
        return radiobutton.containsCssClass(CHECKED_RADIOBUTTON_CSS_CLASS_NAME);
    }

    public void select() {
        if (!hasChecked()) {
            this.radiobutton.click();
        } else {
            log.warn("Radio button: '{}' has already selected. Skip.", radiobutton.getLocator());
        }
    }

    public void shouldBeSelected() {
        radiobutton.shouldHas(cssClassContains(CHECKED_RADIOBUTTON_CSS_CLASS_NAME));
    }

    public WebElm getRadiobuttonElm() {
        return radiobutton;
    }
}