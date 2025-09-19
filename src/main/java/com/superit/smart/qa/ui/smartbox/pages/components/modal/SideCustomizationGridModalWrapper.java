package com.superit.smart.qa.ui.smartbox.pages.components.modal;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;


/**
 * Created by R0manL.
 */

@Getter
@Slf4j
public class SideCustomizationGridModalWrapper {
    private static final WebElm ROOT_ELM = $("control-it-side-customization-grid-modal");
    private static final WebElm ID_OPTIONS_ELMS = ROOT_ELM.$$("control-it-side-customization-grid-modal-cell");
    private static final WebElm DROP_TO_SELECTOR = $(".cdk-drop-list.selected-items-list");

    private final WebElm input;

    public SideCustomizationGridModalWrapper(WebElm expandInput) {
        this.input = expandInput;
    }

    public void searchAndSelectBy(@NotNull String value) {
        new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, ROOT_ELM.$(".available-data-head "))
                .search(value);

        dragAndDropTo(value, DROP_TO_SELECTOR);
    }

    public void searchAndSelectAccountId(@NotNull String value) {
        WebElm searchFieldElm = ROOT_ELM.$("//input[contains(@data-attr-qa, 'tableView-search-field-Account ID')]");
        new InputWrapper(searchFieldElm)
                .clear()
                .setValue(value);

        dragAndDropTo(value, DROP_TO_SELECTOR);
    }


    public SideCustomizationGridModalWrapper expand() {
        input.click();
        return this;
    }

    public SideCustomizationGridModalWrapper close() {
        new ButtonWithIconWrapper(CLOSE, ROOT_ELM.$("control-it-modal-head ")).click();
        ROOT_ELM.should(disappear);

        return this;
    }

    public void dragAndDropTo(@NotNull String value, @NotNull WebElm areaDropToSelector) {
        ID_OPTIONS_ELMS
                .getByText(value)
                .last()
                .dragAndDropTo(areaDropToSelector);
    }
}
