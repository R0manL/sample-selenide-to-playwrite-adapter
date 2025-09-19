package com.superit.smart.qa.ui.core.components.dropdown;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.utils.WaitUtils.waitOnSpinnerDisappear;


@Slf4j
public class DropdownWithSearchWrapper extends DropdownWrapper {
    public DropdownWithSearchWrapper(WebElm select) {
        super(select);
    }

    public WebElm getSearchElm() {
        return new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, DROPDOWN).getInput();
    }

    public WebElm getNoResultsElm() {
        return DROPDOWN.$(".no-results");
    }

    @Override
    public DropdownWithSearchWrapper expand() {
        super.expand();
        return this;
    }

    public DropdownWithSearchWrapper search(@NotNull String text) {
        waitOnSpinnerDisappear(DROPDOWN);
        new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, DROPDOWN).search(text);
        waitOnSpinnerDisappear(DROPDOWN);

        return this;
    }

    public DropdownWithSearchWrapper searchThenSelect(@NotNull String text) {
        search(text).selectBy(text);
        DROPDOWN.should(disappear);
        return this;
    }

    public void searchThenSelectByExactText(@NotNull String text) {
        search(text).selectByExact(text);
        DROPDOWN.should(disappear);
    }

    public void expandThenSearchAndSelect(@NotNull String text) {
        expand().searchThenSelect(text);
        DROPDOWN.should(disappear);
    }
}
