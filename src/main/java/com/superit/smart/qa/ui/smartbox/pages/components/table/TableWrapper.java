package com.superit.smart.qa.ui.smartbox.pages.components.table;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.superit.smart.qa.core.playwright.ModifierKey.CONTROL;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.keyboard;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class TableWrapper {
    private final WebElm tableContainer;
    private final WebElm leftPinnedColsContainer;
    private final WebElm leftPinnedHeadersContainer;


    public TableWrapper(WebElm parent) {
        this.tableContainer = parent.$("control-it-ag-grid-table");
        this.leftPinnedColsContainer = this.tableContainer.$(".ag-pinned-left-cols-container");
        this.leftPinnedHeadersContainer = this.tableContainer.$(".ag-pinned-left-header");
    }

    public WebElm getTableRootElm() {
        return this.tableContainer;
    }

    public WebElm getLeftPinnedColsContainer() {
        return this.leftPinnedColsContainer;
    }

    public TableRowWrapper getRowWrapperBy(List<String> cellValues) {
        return new TableRowWrapper(cellValues, tableContainer);
    }

    public TableRowWrapper getRowWrapperBy(int rowIndex) {
        return new TableRowWrapper(rowIndex, tableContainer);
    }

    public WebElm getRowBy(List<String> cellValues) {
        return new TableRowWrapper(cellValues, tableContainer).getRowElm();
    }

    public WebElm getColumnHeadersFor(@NotNull String groupName) {
        return tableContainer.$$("//*[@role='rowgroup' " +
                "and .//*[@class='ag-header-group-text' and text()='" + groupName + "']]//control-it-custom-header");
    }

    public WebElm getColumnHeaders() {
        return tableContainer.$$("control-it-custom-header");
    }

    public WebElm getLeftPinnedColumnHeaders() {
        return leftPinnedHeadersContainer.$("//*[contains(@class,'header-content')]");
    }

    public WebElm getColumnHeaderBy(@NotNull String colId) {
        return tableContainer.$("[role='columnheader'][col-id='" + colId + "']");
    }

    public WebElm getRowsFromLeftPinnedContainer() {
        return leftPinnedColsContainer.$$("[role='row']");
    }

    public WebElm getCellBy(@NotNull String colId, int rowIndex) {
        log.info("Get cell by row-id: '{}' and col-id: '{}'.", rowIndex, colId);

        return tableContainer.$("//*[@role='row' and @row-index='"
                        + rowIndex + "']/*[@col-id='"
                        + colId + "'][not(@role='columnheader')]")
                .filterByVisibilityIs(true);
    }

    public WebElm getCellsBy(@NotNull String columnId) {
        log.info("Get all cells with col-id: '{}'.", columnId);
        return tableContainer.$$("[col-id='" + columnId + "']:not([role='columnheader'])");
    }

    public WebElm getCellsBy(int rowIndex) {
        return tableContainer.$$("[role='row'][row-index='" + rowIndex + "'] [role='gridcell']").filterByVisibilityIs(true);
    }

    public void updateCellWithTextarea(@NotNull String cellColId, @NotNull String newValue, List<String> rowCellValues) {
        getRowWrapperBy(rowCellValues)
                .getCell(cellColId)
                .updateTextareaWith(newValue);
    }

    public void updateCellWithInput(@NotNull String cellColId, @NotNull String newValue, List<String> rowCellValues) {
        getRowWrapperBy(rowCellValues)
                .getCell(cellColId)
                .updateInputWith(newValue);
    }

    public void updateCellWithDropdown(@NotNull String cellColId, @NotNull String newValue, List<String> rowCellValues) {
        getRowWrapperBy(rowCellValues)
                .getCell(cellColId)
                .selectDropdownWith(newValue);
    }

    public void updateCellWithInput(@NotNull String cellColId, int rowIndex, @NotNull String newValue) {
        getRowWrapperBy(rowIndex)
                .getCell(cellColId)
                .updateInputWith(newValue);
    }

    public void updateCellWithInput(@NotNull String cellColId, int rowIndex, LocalDate newValue) {
        getRowWrapperBy(rowIndex)
                .getCell(cellColId)
                .updateInputWith(newValue);
    }

    public void updateCellWithDropdown(@NotNull String cellColId, int rowIndex, @NotNull String newValue) {
        getRowWrapperBy(rowIndex)
                .getCell(cellColId)
                .selectDropdownWith(newValue);
    }

    public void updateCellWithTextarea(@NotNull String cellColId, int rowIndex, @NotNull String newValue) {
        getRowWrapperBy(rowIndex)
                .getCell(cellColId)
                .updateTextareaWith(newValue);
    }

    /**
     * Selecting any number of cell in table rows
     * by keeping 'CONTROL' key down and clicking at cell
     *
     * @param cellElms any number of table row cells elements (LocatorActions)
     */

    public void selectCells(WebElm... cellElms) {
        log.info("Selecting: '{}' cells in the table.", cellElms.length);
        keyboard().down(CONTROL.toString());
        Arrays.stream(cellElms).forEach(WebElm::click);
        keyboard().up(CONTROL.toString());
    }

    public void expandAllGroups() {
        waitOnLoadersDisappearAndCheckErrors();
        WebElm expandAllGroupsBtn = leftPinnedHeadersContainer
                .$(".left-group-bar .group-btn")
                .get(0)
                .shouldBe(visible)
                .$(".icon-plus");

        if (expandAllGroupsBtn.isDisplayed()) {
            expandAllGroupsBtn.click();
        } else {
            log.warn("Expand all groups have already clicked. Skip.");
        }
    }

    public void waitOnTableHasLoaded() {
        waitOnLoadersDisappearAndCheckErrors(tableContainer);
    }
}
