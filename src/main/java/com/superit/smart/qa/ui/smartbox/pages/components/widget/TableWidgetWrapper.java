package com.superit.smart.qa.ui.smartbox.pages.components.widget;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ConfirmModalWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.table.TableRowWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.table.TableWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.sleepForWebElmRefreshDuration;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.*;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static com.superit.smart.qa.utils.WaitUtils.clickAndWaitOnWidgetDefaultValuesResponse;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class TableWidgetWrapper extends WidgetWrapper {
    private final TableWrapper tableWrapper;

    public TableWidgetWrapper(WidgetID value) {
        super(value);
        this.tableWrapper = new TableWrapper(widgetRootElm);
    }

    public TableWrapper getTableWrapper() {
        return this.tableWrapper;
    }

    public WebElm getRowBy(List<String> cellValues) {
        return tableWrapper.getRowBy(cellValues);
    }

    public WebElm getRowBy(@NotNull String uniqueValue) {
        return getRowBy(List.of(uniqueValue));
    }

    public WebElm getRowBy(LocalDate uniqueValue) {
        String uniqueTextValue = convertDateToStringInFormat(uniqueValue, DatePattern.UI_DATE_FORMAT);
        return getRowBy(uniqueTextValue);
    }

    public TableRowWrapper getRowWrapperBy(List<String> cellValues) {
        return tableWrapper.getRowWrapperBy(cellValues);
    }

    public WebElm getCellsBy(@NotNull String columnId) {
        return tableWrapper.getCellsBy(columnId);
    }

    public TableWidgetWrapper search(@NotNull String text) {
        new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, widgetRootElm).search(text);

        return this;
    }

    public TableWidgetWrapper search(LocalDate value) {
        String text = convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
        return search(text);
    }

    public void updateTableCellTextareaWith(@NotNull String cellColId, @NotNull String newValue, List<String> rowCellValues) {
        tableWrapper.updateCellWithTextarea(cellColId, newValue, rowCellValues);
    }

    public void updateTableCellInput(@NotNull String cellColId, @NotNull String newValue, List<String> rowCellValues) {
        tableWrapper.updateCellWithInput(cellColId, newValue, rowCellValues);
    }

    public void updateTableCellInput(@NotNull String cellColId, int newValue, List<String> rowCellValues) {
        updateTableCellInput(cellColId, String.valueOf(newValue), rowCellValues);
    }

    public void updateTableDropdown(@NotNull String cellColId, String newValue, List<String> rowCellValues) {
        tableWrapper.updateCellWithDropdown(cellColId, newValue, rowCellValues);
    }

    public void updateTableCellInput(@NotNull String cellColId, float newValue, List<String> rowCellValues) {
        updateTableCellInput(cellColId, String.valueOf(newValue), rowCellValues);
    }

    /**
     * Search and remove record by unique cells' values.
     *
     * @param cellValues       - text values in the row.
     * @param searchValueIndex - index of values to search.
     */
    public void removeIfFind(List<String> cellValues, int searchValueIndex) {
        waitOnLoadersDisappearAndCheckErrors(widgetRootElm);

        if (!emptyDataHolder.isDisplayed()) {
            search(cellValues.get(searchValueIndex));
            sleepForWebElmRefreshDuration();

            TableRowWrapper row = tableWrapper.getRowWrapperBy(cellValues);

            if (row.isDisplayed()) {
                row.selectAgCheckBox(true);
                clickDelete().clickYesDelete();
            } else {
                log.debug("No row has been found with values: " + cellValues);
            }
        } else {
            log.warn("Entity list is empty. Nothing to delete, skip.");
        }
    }

    public void remove(List<String> cellValues, int searchValueIndex) {
        waitOnLoadersDisappearAndCheckErrors(widgetRootElm);

        String searchValue = cellValues.get(searchValueIndex);
        if (searchValue != null) {
            search(searchValue);
            sleepForWebElmRefreshDuration(2);
        } else {
            log.warn("Can't search by 'null' value. Skip.");
        }

        TableRowWrapper row = getRowWrapperBy(cellValues);
        row.selectAgCheckBox(true);
        clickDelete().clickYesDelete();
    }

    public ConfirmModalWrapper clickSave() {
        getSaveButton().click();
        return new ConfirmModalWrapper();
    }

    public WebElm getSaveButton() {
        return new ButtonWithIconWrapper(CONFIRM, widgetRootElm).getWebElm();
    }

    public void clickAdd() {
        new ButtonWithIconWrapper(ADD, widgetRootElm).getWebElm().click();
    }

    public void clickCancel() {
        new ButtonWithIconWrapper(CANCEL, widgetRootElm).getWebElm().click();
    }

    public void clickAddAndWaitOnDefaultValuesWillLoad() {
        WebElm btn = new ButtonWithIconWrapper(ADD, widgetRootElm).getWebElm();
        clickAndWaitOnWidgetDefaultValuesResponse(btn); // Click and wait until default values will load.
    }

    public ConfirmModalWrapper clickCopy() {
        new ButtonWithIconWrapper(COPY, widgetRootElm).getWebElm().click();
        return new ConfirmModalWrapper();
    }

    public TableWidgetWrapper clickEdit() {
        new ButtonWithIconWrapper(EDIT, widgetRootElm).getWebElm().click();
        sleepForWebElmRefreshDuration(2); // this delay is needed for edit mode to be displayed. 'Element is not attached to the DOM' fails the tests if there is no delay
        return this;
    }

    protected ConfirmModalWrapper clickDelete() {
        new ButtonWithIconWrapper(DELETE, widgetRootElm).click();
        return new ConfirmModalWrapper();
    }
}
