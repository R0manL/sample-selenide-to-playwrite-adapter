package com.superit.smart.qa.ui.smartbox.pages.panels;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.HistoryCard;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;

/**
 * Created on 21.07.2022
 */

@Slf4j
public class HistoryPanel {

    private static final WebElm ROOT_ELM = $("control-it-history-modal");


    public HistoryCard getHistoryCard(int index) {
        log.info("Getting first history card element.");
        WebElm firstCardHistoryElm = getHistoryCardElms().get(index);
        return new HistoryCard(firstCardHistoryElm);
    }

    public DropdownWithSearchWrapper getHistorySearchDropdown() {
        log.info("Getting history search dropdown.");
        WebElm dropdownElm = ROOT_ELM.$("//control-it-dropdown");

        return new DropdownWithSearchWrapper(dropdownElm);
    }

    public HistoryPanel clearHistorySearchDropdown() {
        log.info("Clear history search dropdown.");
        getHistorySearchDropdown().getSelectedOption().$(".icon-closed").click();

        return this;
    }

    public WebElm getHistoryCardElms() {
        log.info("Getting history card elements.");
        return ROOT_ELM.$$(".card-history-item");
    }

    public WebElm getFilterButton(ButtonName buttonName) {
        log.info("Get filter button.");
        return ROOT_ELM.$(".filter-btn").$("//button[.//span[normalize-space(text())='" + buttonName + "']]");
    }

    public void setAndApplyFilter(@NotNull LocalDate dateFrom, @NotNull LocalDate dateTo, TypeOfChange typeOfChange) {
        setInputValue(InputCaption.FROM, convertDateToStringInFormat(dateFrom, DatePattern.UI_DATE_FORMAT));
        setInputValue(InputCaption.TO, convertDateToStringInFormat(dateTo, DatePattern.UI_DATE_FORMAT));
        new DropdownWithSearchWrapper(getDropdownSelectElm(Dropdown.TYPE_OF_CHANGE)).expand().scrollAndSelectFirstByText(typeOfChange.toString());

        getFilterButton(HistoryPanel.ButtonName.APPLY_FILTERS).click();
    }

    public WebElm getListOfHistoryCards() {
        return ROOT_ELM.$(".card-history-list").$$("control-it-card-history");
    }

    public WebElm getInputValue(InputCaption inputCaption) {
        return $("//div[contains(@class, 'field-item') and .//*[normalize-space(text())='" + inputCaption + "']]//input");
    }

    public WebElm getDropdownSelectElm(@NotNull Dropdown dropdown) {
        return ROOT_ELM.$("//*[@class='field-item-full' and .//*[contains(text(),'" + dropdown + "')]]/control-it-dropdown//mat-form-field/..");
    }

    private void setInputValue(InputCaption inputCaption, @NotNull String value) {
        getInputValue(inputCaption).val(value);
    }

    public enum InputCaption {
        FROM("from:"),
        TO("to:");

        private final String title;

        InputCaption(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum Dropdown {
        TYPE_OF_CHANGE("Type of change:");

        private final String title;

        Dropdown(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }


    public enum ButtonName {
        MORE_FILTERS("More filters"),
        HIDE_FILTERS("Hide filters"),
        APPLY_FILTERS("Apply filters"),
        RESET_ALL_FILTERS("Reset all filters");

        private final String title;

        ButtonName(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum TypeOfChange {
        MANUAL("Manual changes"),
        AUTOMATED("Automated changes");

        private final String title;

        TypeOfChange(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

}
