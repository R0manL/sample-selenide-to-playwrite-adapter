package com.superit.smart.qa.ui.smartbox.pages.popups;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.BaseVersion;
import com.superit.smart.qa.ui.smartbox.enums.CalculationShowResultIn;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfDate;
import com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation;


import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

@Slf4j
public class CalculationDetailsSettings extends CustomizeCalculation {
    private static final WebElm WIDGET_ROOT_ELM = $("control-it-side-customization");


    public WebElm getRootElement() {
        return WIDGET_ROOT_ELM;
    }

    public CalculationDetailsSettings selectCompareVersionBy(@NotNull String value) {
        new DropdownWrapper(getSelectDropdownElmFor(Dropdown.COMPARE_VERSION))
                .expand()
                .scrollAndSelectFirstByText(value);

        return this;
    }

    @Override
    public CalculationDetailsSettings selectAccountsBy(@NotNull String... names) {
        super.selectAccountsBy(names);

        return this;
    }

    public CalculationDetailsSettings clearAccountsDropdown() {
        super.clearAccounts();

        return this;
    }

    public CalculationDetailsSettings clearHierarchyDropdown() {
        super.clearHierarchyLevel();

        return this;
    }

    @Override

    public CalculationDetailsSettings selectEntityLevel(@NotNull String name) {
        super.selectEntityLevel(name);

        return this;
    }

    @Override
    public CalculationDetailsSettings selectStartPeriodFuture(@NotNull String period) {
        super.selectStartPeriodFuture(period);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectAccountStructureBy(@NotNull String name) {
        super.selectAccountStructureBy(name);
        return this;
    }

    @Override
    public CalculationDetailsSettings applyRules() {
        super.applyRules();
        return this;
    }

    @Override
    public CalculationDetailsSettings selectKPIs(@NotNull String... kpis) {
        super.selectKPIs(kpis);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectHierarchyLevel(Level level) {
        super.selectHierarchyLevel(level);
        return this;
    }

    @Override
    public CalculationDetailsSettings clickResetToDefault() {
        super.clickResetToDefault();
        return this;
    }

    @Override
    public CalculationDetailsSettings clickShowHistoryCheckbox() {
        super.clickShowHistoryCheckbox();
        return this;
    }

    @Override
    public CalculationDetailsSettings clickShowFutureCheckbox() {
        super.clickShowFutureCheckbox();
        return this;
    }

    @Override
    public CalculationDetailsSettings setShowFutureAmount(int value) {
        super.setShowFutureAmount(value);
        return this;
    }

    @Override
    public CalculationDetailsSettings setShowHistoryAmount(int value) {
        super.setShowHistoryAmount(value);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectBaseVersionForFuture(BaseVersion baseVersion) {
        super.selectBaseVersionForFuture(baseVersion);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectTypeOfDateForFuture(TypeOfDate typeOfDate) {
        super.selectTypeOfDateForFuture(typeOfDate);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectTypeOfDateForHistory(TypeOfDate typeOfDate) {
        super.selectTypeOfDateForHistory(typeOfDate);
        return this;
    }

    @Override
    public CalculationDetailsSettings selectCalcComparisonView(ComparisonViewsRadioButton comparisonViewsRadioButton) {
        super.selectCalcComparisonView(comparisonViewsRadioButton);
        return this;
    }

    @Override
    public CalculationDetailsSettings clickCompareWithOtherResultsCheckbox() {
        super.clickCompareWithOtherResultsCheckbox();
        return this;
    }

    @Override
    public CalculationDetailsSettings selectShowResultIn(CalculationShowResultIn showResultIn) {
        super.selectShowResultIn(showResultIn);
        return this;
    }

}
