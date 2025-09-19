package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.DashboardDeviation;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.widgets.DashboardTileViewWidget;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithMultiSelectWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.core.playwright.conditions.Condition.enabled;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.RESET_TO_DEFAULT;

@Slf4j
public class DashboardSettings {
    private static final WebElm ROOT_ELM = $(".app-aside.is-right.is-show");

    public void clickClosePanel() {
        new ButtonWithIconWrapper(ButtonWithIconWrapper.IconClassName.CLOSE, ROOT_ELM.$(".app-aside-head"))
                .click();

        ROOT_ELM.should(disappear);
    }

    private DashboardSettings expandDropdown(@NotNull Dropdown dropdown) {
        log.info("Expand dropdown: '{}'", dropdown);
        ROOT_ELM.$("//*[contains(@class,'app-aside-sidebar-items') " +
                        "and .//*[normalize-space(text())='" + dropdown + "']]//mat-select")
                .click();

        return this;
    }

    private enum Dropdown {
        ACTUAL_PERIOD("Actual period *:"),
        COMPARE_WITH("Compare with:"),
        CURRENCY_SHOW_VALUES_AS_IN("Show values as in *:"),
        DEVIATION("Deviation:");

        private final String title;

        Dropdown(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
