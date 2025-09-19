package com.superit.smart.qa.ui.smartbox.pages.popups;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.BaseVersion;
import com.superit.smart.qa.ui.smartbox.enums.CalculationShowResultIn;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfDate;
import com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.ADD;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.APPLY_AND_SHOW_RESULTS;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class CalculationPreviewSettings extends CustomizeCalculation {
    private static final WebElm WIDGET_ROOT_ELM = $("control-it-side-customization");


    public WebElm getRootElement() {
        return WIDGET_ROOT_ELM;
    }

    @Override
    public CalculationPreviewSettings selectAccountsBy(@NotNull String... name) {
        super.selectAccountsBy(name);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectAccountStructureBy(@NotNull String name) {
        super.selectAccountStructureBy(name);
        waitOnLoadersDisappearAndCheckErrors();
        return this;
    }

    @Override
    public CalculationPreviewSettings applyRules() {
        new ButtonWithTextWrapper(APPLY_AND_SHOW_RESULTS, getRootElement()).click();
        return this;
    }

    @Override
    public CalculationPreviewSettings selectKPIs(@NotNull String... kpis) {
        super.selectKPIs(kpis);
        return this;
    }

    @Override
    public CalculationPreviewSettings clickResetToDefault() {
        super.clickResetToDefault();

        return this;
    }

    @Override
    public CalculationPreviewSettings selectHierarchyLevel(Level level) {
        super.selectHierarchyLevel(level);

        return this;
    }

    @Override

    public CalculationPreviewSettings selectEntityLevel(@NotNull String name) {
        super.selectEntityLevel(name);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectShowValues(@NotNull ShowValues showValues) {
        super.selectShowValues(showValues);

        return this;
    }

    @Override
    public CalculationPreviewSettings selectDecimals(@NotNull Decimals decimals) {
        super.selectDecimals(decimals);

        return this;
    }

    @Override
    public CalculationPreviewSettings clickShowHistoryCheckbox() {
        super.clickShowHistoryCheckbox();
        return this;
    }

    @Override
    public CalculationPreviewSettings clickShowFutureCheckbox() {
        super.clickShowFutureCheckbox();
        return this;
    }

    @Override
    public CalculationPreviewSettings setShowFutureAmount(int value) {
        super.setShowFutureAmount(value);
        return this;
    }

    @Override
    public CalculationPreviewSettings setShowHistoryAmount(int value) {
        super.setShowHistoryAmount(value);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectTypeOfDateForFuture(TypeOfDate typeOfDate) {
        super.selectTypeOfDateForFuture(typeOfDate);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectTypeOfDateForHistory(TypeOfDate typeOfDate) {
        super.selectTypeOfDateForHistory(typeOfDate);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectBaseVersionForFuture(BaseVersion baseVersion) {
        super.selectBaseVersionForFuture(baseVersion);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectBaseVersionForHistory(BaseVersion baseVersion) {
        super.selectBaseVersionForHistory(baseVersion);
        return this;
    }

    @Override
    public CalculationPreviewSettings selectStartPeriodFuture(@NotNull String period) {
        super.selectStartPeriodFuture(period);
        return this;
    }

    @Override
    public WebElm getCalendarYearsRadioButton() {
        return super.getCalendarYearsRadioButton();
    }

    @Override
    public WebElm getFiscalYearsRadioButton() {
        return super.getFiscalYearsRadioButton();
    }

    @Override
    public boolean isRadioButtonSelected(WebElm radioButton) {
        return super.isRadioButtonSelected(radioButton);
    }
    @Override
    public CalculationPreviewSettings selectShowResultIn(CalculationShowResultIn showResultIn) {
        super.selectShowResultIn(showResultIn);
        return this;
    }
}
