package com.superit.smart.qa.ui.smartbox.pages.components.table;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.TippyToolTipWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchAndMultiSelectWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;

@Slf4j
@Getter
public class TableCellWrapper {
    private final WebElm cell;

    /**
     * Define table's cell by:
     *
     * @param colId - col-id value
     * @param row   - where cell located
     */

    public TableCellWrapper(@NotNull String colId, WebElm row) {
        this.cell = row.$("[col-id='" + colId + "']:not([role='columnheader']) .table-cell");
    }

    public InputEditor editWith(@NotNull String newValue) {
        return new InputEditor().editWith(newValue);
    }

    public void updateInputWith(@NotNull String newValue) {
        editWith(newValue)
                .completeEditing();
    }

    public void updateInputWith(LocalDate newValue) {
        String value = convertDateToStringInFormat(newValue, DatePattern.UI_DATE_FORMAT);
        updateInputWith(value);
    }

    public void updateTextareaWith(@NotNull String newValue) {
        cell.scrollIntoView();
        sleepForWebElmRefreshDuration(2);
        // focus to cell if it is not in focus (lined green)
        if (!cell.$("//ancestor::div[contains(@class, 'ag-cell-value')]").containsCssClass("ag-cell-focus")) {
            cell.click();
            sleepForWebElmRefreshDuration(2);
        }

        //click to open textarea
        cell.click();
        sleepForWebElmRefreshDuration(2);

        //click one more time if textarea is not displayed
        WebElm input = $("textarea");
        if (!input.isDisplayed()) {
            cell.click();
            sleepForWebElmRefreshDuration(2);
        }

        new InputWrapper(input)
                .clearWithBackspace()
                .sendKeys(newValue)
                .pressEnter();

        input.should(disappear);
        sleepForWebElmRefreshDuration(2); //Note. Sometimes app need more time to identify changes.
    }

    public void selectDropdownWith(@NotNull String newValue) {
        cell.scrollIntoView();
        cell.doubleClick();

        WebElm dropdownElm = cell.$("control-it-dropdown");
        DropdownWithSearchAndMultiSelectWrapper dropdownWrapper = new DropdownWithSearchAndMultiSelectWrapper(dropdownElm);
        if (dropdownWrapper.getSearchElm().isDisplayed()) {
            dropdownWrapper.searchAndSelectOptionsBy(newValue);
        } else {
            dropdownWrapper.selectBy(newValue);
        }

        dropdownElm.should(disappear);
        sleepForWebElmRefreshDuration(); //Note.required some time to update DOM after previous update.
    }

    public class InputEditor {
        private static final WebElm INPUT_EDITOR = $("control-it-base-number-cell-editor, control-it-date-cell-editor");
        private static final WebElm INPUT = INPUT_EDITOR.$("input");

        public InputEditor editWith(@NotNull String newValue) {
            sleepForWebElmRefreshDuration(4); //'Element is not attached to the DOM'
            cell.scrollIntoView();
            sleepForWebElmRefreshDuration(2); //'Element is not attached to the DOM'
            cell.doubleClick();

            sleepForWebElmRefreshDuration(2);
            if (!INPUT.isDisplayed()) {
                cell.click();
                sleepForWebElmRefreshDuration();
            }

            WebElm input = INPUT.shouldBe(visible);
            input.click();

            new InputWrapper(input)
                    .clear()
                    .sendKeys(newValue);

            return this;
        }

        public void completeEditing() {
            if (INPUT.isDisplayed()) {
                log.info("Complete editing.");
                INPUT.click();
                sleepForWebElmRefreshDuration();
                new InputWrapper(INPUT).pressEnter();

                INPUT_EDITOR.shouldBe(hidden);
                sleepForWebElmRefreshDuration(2); //Note. Sometimes app need more time to identify changes.
            } else {
                log.warn("Input editor is not displayed. Skip.");
            }
        }

        public WebElm getErrorMessage() {
            INPUT_EDITOR.$("[tippy]").hover();
            return new TippyToolTipWrapper().getContentElm();
        }
    }
}
