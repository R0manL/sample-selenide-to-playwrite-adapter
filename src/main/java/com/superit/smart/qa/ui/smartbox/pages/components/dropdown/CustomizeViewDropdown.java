package com.superit.smart.qa.ui.smartbox.pages.components.dropdown;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder.SEARCH;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CUSTOMIZE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.SETTINGS;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;
import static com.superit.smart.qa.utils.WaitUtils.waitOnSpinnerDisappear;

@Slf4j
public class CustomizeViewDropdown {
    private static final WebElm ROOT_ELEMENT = $("control-it-customization-view-dropdown .dropdown-content");
    private static final WebElm CUSTOMIZATION_LIST_ROOT_ELM = ROOT_ELEMENT.$(".scroll-content");
    private static final WebElm CUSTOMIZATIONS = CUSTOMIZATION_LIST_ROOT_ELM.$(".dropdown-item");


    public CustomizeViewDropdown clickSetupView() {
        log.info("Click setup views.");
        new ButtonWithIconWrapper(CUSTOMIZE, ROOT_ELEMENT).click();
        ROOT_ELEMENT.should(disappear);

        return this;
    }

    public CustomizeViewDropdown search(@NotNull String name) {
        waitOnLoadersDisappearAndCheckErrors(); //Note. If search before page has loaded, page may refresh and we may select wrong item.
        new InputWithPlaceholderWrapper(SEARCH, ROOT_ELEMENT).sendKeys(name);
        waitOnSpinnerDisappear(ROOT_ELEMENT);
        return this;
    }

    private void select(@NotNull String name) {
        get(name).click();
    }

    public WebElm get(@NotNull String name) {
        return CUSTOMIZATIONS.filterByChild("//*[normalize-space(text())='" + name +
                "' and contains(@class, 'value-name')]");
    }

    public void searchSelectIfNotSelected(@NotNull String name) {
        search(name);

        if (!hasSelected(name)) {
            select(name);
            sleepForWebElmRefreshDuration(6); //Note. Sometimes after customize view selection, page start reloading after 3-4sec.
            waitOnLoadersDisappearAndCheckErrors(ROOT_ELEMENT);
        } else {
            log.warn("Customize view: '{}', has already selected. Collapse dropdown.", name);
            collapse();
        }
    }

    public void collapse() {
        removeFocus();
    }

    public boolean hasSelected(@NotNull String name) {
        return get(name).$(".value-name").containsCssClass("is-applied");
    }

    public void edit(@NotNull String name) {
        log.info("Click edit customize view: '{}'.", name);

        new ButtonWithIconWrapper(SETTINGS, get(name)).click();
    }
}
