package com.superit.smart.qa.ui.smartbox.pages.components.table;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.checkbox.CheckboxAgWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.checkbox.CheckboxMatWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TableRowWrapper {
    private final WebElm rootElm;


    public TableRowWrapper(List<String> cellValues, WebElm parent) {
        StringBuilder xpath = new StringBuilder("//*[@row-index");
        for (String val : cellValues) {
            xpath.append(" and .//*[normalize-space(.)='").append(val).append("']");
        }

        xpath.append("]");

        this.rootElm = parent.$(xpath.toString());
    }

    public TableRowWrapper(int rowIndex, WebElm parent) {
        this.rootElm = parent.$("[row-index='" + rowIndex + "']");
    }

    public WebElm getRowElm() {
        return rootElm;
    }

    public void selectAgCheckBox(boolean value) {
        hover();
        new CheckboxAgWrapper(this.rootElm).select(value);
    }

    public void selectMatCheckBox(boolean value) {
        hover();
        new CheckboxMatWrapper(this.rootElm).select(value);
    }

    public boolean isDisplayed() {
        return rootElm.isDisplayed();
    }

    public TableCellWrapper getCell(@NotNull String colId) {
        return new TableCellWrapper(colId, rootElm);
    }

    private void hover() {
        this.rootElm
                .scrollIntoView()
                .hover();
    }
}
