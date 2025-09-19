package com.superit.smart.qa.ui.smartbox.pages.components.toggle;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.conditions.Condition.*;


@Slf4j
public class ToggleWrapper {
    private final WebElm toggle;
    private static final String CHECKED_TOGGLE_CSS_CLASS_NAME = "mat-checked";


    public ToggleWrapper(@NotNull String caption, @NotNull WebElm parent) {
        this.toggle = parent.$("//mat-slide-toggle[.//span[@class='mat-slide-toggle-content' " +
                "and normalize-space(text())='" + caption + "']]");
    }


    public boolean hasChecked() {
        return toggle.containsCssClass(CHECKED_TOGGLE_CSS_CLASS_NAME);
    }

    public void select(boolean isSelect) {
        toggle.shouldBe(visible);

        if (isSelect ^ hasChecked()) {
            toggle.click();

            if (isSelect) {
                shouldBeSelected();
            } else {
                toggle.shouldHas(notCssClassContains(CHECKED_TOGGLE_CSS_CLASS_NAME));
            }

        } else {
            log.warn("Toggle has already set. Skip selection.");
        }
    }

    public void shouldBeSelected() {
        toggle.shouldHas(cssClassContains(CHECKED_TOGGLE_CSS_CLASS_NAME));
    }

    public WebElm getToggleInputElm() {
        return toggle.$("//input");
    }
}