package com.superit.smart.qa.ui.smartbox.pages.components.modal;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


/**
 * Created by R0manL.
 */

@Slf4j
public class ListModalWrapper {
    private static final WebElm ROOT_ELM = $("control-it-infinite-list-modal");
    @Getter
    private final WebElm input;
    private static final String CELL_CSS_LOCATOR_TEMPLATE = "[role='gridcell'][col-id='%s']";
    private static final String SEARCH_CELL_XPATH_LOCATOR_TEMPLATE = "//*[@role='gridcell' and @aria-colindex=//control-it-infinite-list-modal//*[@role='columnheader' and @col-id='%s']/@aria-colindex]//input/..";

    public ListModalWrapper(WebElm expandInput) {
        this.input = expandInput;
    }

    public ListModalWrapper expand() {
        input.click();
        return this;
    }

    public void searchAndSelectByStartWith(@NotNull String text, ColID colID) {
        searchBy(text, colID);
        selectByStartWith(text, colID);
    }

    public void searchAndSelectByExactText(@NotNull String text, ColID colID) {
        searchBy(text, colID);
        selectByExactText(text, colID);
    }

    public void searchByTwoColumnsAndSelectByFirstColTextStartWith(@NotNull String firstColText, ColID firstColID, @NotNull String secondColText, ColID secondColID) {
        searchBy(firstColText, firstColID);
        searchBy(secondColText, secondColID);
        selectByStartWith(firstColText, firstColID);
    }

    public void expandSearchAndSelectByStartWith(@NotNull String value, ColID colID) {
        expand();
        searchAndSelectByStartWith(value, colID);
    }

    public void expandAndSelectByExactText(@NotNull String value, ColID colID) {
        expand();
        searchAndSelectByExactText(value, colID);
    }

    public void clickClose() {
        ROOT_ELM.$("button[mat-dialog-close]").click();
    }

    public WebElm getOptionsBy(ColID colID) {
        return ROOT_ELM.$$(String.format(CELL_CSS_LOCATOR_TEMPLATE, colID.getValue()));
    }

    public void selectByExactText(@NotNull String text, ColID colID) {
        getOptionsBy(colID).getByExactText(text).click();
    }

    public void selectByStartWith(@NotNull String prefix, ColID colID) {
        getOptionsBy(colID).getByTextStartWith(prefix).click();
    }

    public ListModalWrapper searchBy(@NotNull String value, ColID colID) {
        getSearchInputWrapperFrom(colID).setValue(value);
        return this;
    }

    public ListModalWrapper cleanSearchInput(ColID colID) {
        getSearchInputWrapperFrom(colID).clear();

        return this;
    }

    public WebElm getSelectedOption() {
        return  this.input;
    }

    private InputWithPlaceholderWrapper getSearchInputWrapperFrom(ColID colID) {
        WebElm fieldElm = ROOT_ELM.$(String.format(SEARCH_CELL_XPATH_LOCATOR_TEMPLATE, colID.getValue()));

        return new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, fieldElm);
    }

    @Getter
    public enum ColID {
        ATTRIBUTE("attribute"),
        DESIGNATION("designationshort"),
        KEY("key"),
        COMMENT("comment"),
        ID("id");

        private final String value;

        ColID(@NotNull String value) {
            this.value = value;
        }
    }
}
