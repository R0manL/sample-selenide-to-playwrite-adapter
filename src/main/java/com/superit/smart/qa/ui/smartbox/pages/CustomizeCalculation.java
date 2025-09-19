package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.BaseVersion;
import com.superit.smart.qa.ui.smartbox.enums.CalculationShowResultIn;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfDate;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.SideCustomizationGridModalWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.radiobutton.RadioButtonWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.toggle.ToggleWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchAndMultiSelectWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation.InputWithDragAndDrop.ACCOUNTS;
import static com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation.YearViews.CALENDAR_YEARS;
import static com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation.YearViews.FISCAL_YEARS;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.*;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.APPLY_AND_SHOW_RESULTS;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public abstract class CustomizeCalculation {

    public abstract WebElm getRootElement();

    protected final WebElm modalHeadElm = getRootElement().$("control-it-modal-head");

    protected CustomizeCalculation selectKPIs(@NotNull String... kpis) {
        SideCustomizationGridModalWrapper modalWrapper = getDropdownWithDragAndDropAndMultiSelectElm(InputWithDragAndDrop.KPI)
                .expand();

        for (String kpi : kpis) {
            modalWrapper.searchAndSelectBy(kpi);
        }

        modalWrapper.close();
        return this;
    }

    protected CustomizeCalculation selectAccountStructureBy(@NotNull String name) {
        log.info("Select '{}' account structure", name);
        getDropdownWrapper(Dropdown.ACCOUNT_STRUCTURE).expand().scrollAndSelectFirstByText(name);

        return this;
    }

    protected CustomizeCalculation selectEntityLevel(@NotNull String entityLevel) {
        new DropdownWrapper(getSelectDropdownElmFor(Dropdown.ENTITY_LEVEL)).expandAndSelectIfNotSelectedBy(entityLevel);

        return this;
    }

    protected CustomizeCalculation selectAccountsBy(@NotNull String... names) {
        log.info("Select Accounts.");
        SideCustomizationGridModalWrapper modalWrapper = getDropdownWithDragAndDropAndMultiSelectElm(ACCOUNTS)
                .expand();

        for (String name : names) {
            modalWrapper.searchAndSelectAccountId(name);
        }
        modalWrapper.close();

        return this;
    }

    protected CustomizeCalculation selectShowValues(@NotNull ShowValues showValuesOption) {
        final String option = showValuesOption.getText();
        log.info("Select '{}' in 'Show values' dropdown.", option);
        getDropdownWrapper(Dropdown.SHOW_VALUES)
                .expand()
                .scrollAndSelectFirstByText(option);

        return this;
    }

    protected CustomizeCalculation selectDecimals(@NotNull Decimals decimalsOption) {
        final String option = decimalsOption.getText();
        log.info("Select '{}' in 'Decimals' dropdown.", option);

        getDropdownWrapper(Dropdown.DECIMALS).expand().scrollAndSelectFirstByText(option);

        return this;
    }

    public CustomizeCalculation applyRules() {
        new ButtonWithTextWrapper(APPLY_AND_SHOW_RESULTS, getRootElement()).click();
        waitOnLoadersDisappearAndCheckErrors();
        return this;
    }

    public void collapse() {
        new ButtonWithIconWrapper(CLOSE, modalHeadElm).click();
        getRootElement().should(disappear);
    }

    protected CustomizeCalculation selectHierarchyLevel(Level level) {
        getDropdownWithSearchAndMultiSelectElm(Dropdown.HIERARCHY_LEVEL)
                .expand()
                .scrollAndSelectFirstByText(level.getText())
                .collapse();

        return this;
    }

    protected void clearAccounts() {
        new InputWrapper(getInputElm(ACCOUNTS)).clear();
    }

    protected void clearHierarchyLevel() {
        getDropdownWithSearchElm(Dropdown.HIERARCHY_LEVEL).removeSelectedOption();
    }

    protected CustomizeCalculation selectStartPeriodFuture(@NotNull String period) {
        getDropdownWithSearchElm(Dropdown.START_PERIOD_FUTURE)
                .expand()
                .searchThenSelect(period);

        return this;
    }

    public CustomizeCalculation clickResetToDefault() {
        log.info("Click reset to default.");
        new ButtonWithIconWrapper(ICON_RESET, getRootElement()).click();

        return this;
    }

    public CustomizeCalculation clickShowHistoryCheckbox() {
        log.info("Click 'Show History' checkbox.");
        new ToggleWrapper(CustomizationAdvancedGroup.SHOW_HISTORY.getLabel(), getRootElement()).select(true);
        return this;
    }

    public CustomizeCalculation clickShowFutureCheckbox() {
        log.info("Click 'Show Future' checkbox.");
        new ToggleWrapper(CustomizationAdvancedGroup.SHOW_FUTURE.getLabel(), getRootElement()).select(true);

        return this;
    }

    public CustomizeCalculation setShowHistoryAmount(int value) {
        new InputWrapper(getInputElm(CustomizationAdvancedGroup.SHOW_HISTORY, CustomizationInput.AMOUNT)).sendKeys(String.valueOf(value));

        return this;
    }

    public CustomizeCalculation setShowFutureAmount(int value) {
        new InputWrapper(getInputElm(CustomizationAdvancedGroup.SHOW_FUTURE, CustomizationInput.AMOUNT))
                .clear()
                .sendKeys(String.valueOf(value));

        return this;
    }

    public CustomizeCalculation selectTypeOfDateForFuture(TypeOfDate typeOfDate) {
        getDropdownWrapper(CustomizationAdvancedGroup.SHOW_FUTURE.getLabel(), Dropdown.TYPE_OF_DATE)
                .expand()
                .scrollAndSelectFirstByText(typeOfDate.getTypeValue());

        return this;
    }

    public CustomizeCalculation selectTypeOfDateForHistory(TypeOfDate typeOfDate) {
        getDropdownWrapper(CustomizationAdvancedGroup.SHOW_HISTORY.getLabel(), Dropdown.TYPE_OF_DATE)
                .expand()
                .scrollAndSelectFirstByText(typeOfDate.getTypeValue());

        return this;
    }

    public CustomizeCalculation selectBaseVersionForFuture(BaseVersion baseVersion) {
        getDropdownWrapper(CustomizationAdvancedGroup.SHOW_FUTURE.getLabel(), Dropdown.BASE_VERSION)
                .expand()
                .scrollAndSelectFirstByText(baseVersion.getTypeValue());

        return this;
    }

    public CustomizeCalculation selectBaseVersionForHistory(BaseVersion baseVersion) {
        getDropdownWrapper(CustomizationAdvancedGroup.SHOW_HISTORY.getLabel(), Dropdown.BASE_VERSION)
                .expand()
                .scrollAndSelectFirstByText(baseVersion.getTypeValue());

        return this;
    }

    protected CustomizeCalculation selectYearsOptionView(YearViews yearViews) {
        return selectRadiobutton(yearViews.getText());
    }

    public CustomizeCalculation clickCompareWithOtherResultsCheckbox() {
        log.info("Click 'Compare with other results' checkbox.");
        new ToggleWrapper(CustomizationAdvancedGroup.COMPARE_WITH_OTHER_RESULTS.getLabel(), getRootElement()).select(true);

        return this;
    }

    public CustomizeCalculation selectCalcComparisonView(ComparisonViewsRadioButton comparisonViewsRadioButton) {
        selectRadiobutton(comparisonViewsRadioButton.getText());
        return this;
    }

    public CustomizeCalculation selectShowResultIn(CalculationShowResultIn showResultIn) {
        getDropdownWrapper(Dropdown.SHOW_RESULTS_IN)
                .expand()
                .scrollAndSelectFirstByExactText(showResultIn.getValue());

        return this;
    }

    public DropdownWrapper getDropdownWrapper(Dropdown dropdown) {
        return new DropdownWrapper(getSelectDropdownElmFor(dropdown));
    }

    public DropdownWrapper getDropdownWrapper(String section, Dropdown dropdown) {
        return new DropdownWrapper(getSelectDropdownElmFor(section, dropdown));
    }

    protected boolean isRadioButtonSelected(WebElm radioButton) {
        return new RadioButtonWrapper(radioButton).hasChecked();
    }

    protected WebElm getCalendarYearsRadioButton() {
        return new RadioButtonWrapper(CALENDAR_YEARS.getText(), getRootElement()).getRadiobuttonElm();
    }

    protected WebElm getFiscalYearsRadioButton() {
        return new RadioButtonWrapper(FISCAL_YEARS.getText(), getRootElement()).getRadiobuttonElm();
    }

    protected WebElm getSelectedValueIn(CustomizeCalculation.Dropdown dropdown) {
        return new DropdownWrapper(getSelectDropdownElmFor(dropdown)).getSelectedOption();
    }

    private DropdownWithSearchWrapper getDropdownWithSearchElm(Dropdown dropdown) {
        return new DropdownWithSearchWrapper(getSelectDropdownElmFor(dropdown));
    }

    private DropdownWithSearchAndMultiSelectWrapper getDropdownWithSearchAndMultiSelectElm(Dropdown dropdown) {
        return new DropdownWithSearchAndMultiSelectWrapper(getSelectDropdownElmFor(dropdown));
    }

    private SideCustomizationGridModalWrapper getDropdownWithDragAndDropAndMultiSelectElm(InputWithDragAndDrop input) {
        return new SideCustomizationGridModalWrapper(getInputElm(input));
    }

    private CustomizeCalculation selectRadiobutton(String label) {
        log.info("Click '{}' radiobutton.", label);

        WebElm radioBtn = getRootElement()
                .$("//mat-radio-button[.//*[normalize-space(text())='" + label + "']]");

        if (!new RadioButtonWrapper(radioBtn).hasChecked()) {
            radioBtn.$("label").click();
        } else {
            log.info(label + " is already selected. Skip this step.");
        }

        return this;
    }

    protected WebElm getSelectDropdownElmFor(CustomizeCalculation.Dropdown dropdown) {
        return getRootElement().$("//*[contains(@class,'block-item') " +
                "and ./*[normalize-space(text())='" + dropdown + "']]//mat-select");
    }

    protected WebElm getSelectDropdownElmFor(String toggleSectionText, CustomizeCalculation.Dropdown dropdown) {
        return getRootElement().$("//*[@class='block-item item-bottom-separator full-width' " +
                "and .//*[normalize-space(text())='" + toggleSectionText + "']]//*[contains(@class,'block-item') " +
                "and ./*[normalize-space(text())='" + dropdown + "']]//mat-select");
    }

    public WebElm getInputElm(CustomizationAdvancedGroup toggle, CustomizationInput input) {
        return $("//*[@class='block-item item-bottom-separator full-width' " +
                "and .//*[normalize-space(text())='" + toggle.getLabel() + "']]//div[@class= 'block-item' " +
                "and .//*[contains(@class, 'text-small') and normalize-space(text())='" + input.getLabel() + "']]//input");
    }

    public WebElm getInputElm(InputWithDragAndDrop input) {
        return $("//div[contains(@class, 'block-item') and ./*[contains(@class, 'text-small') " +
                "and normalize-space(text())='" + input.getValue() + "']]//input");
    }

    public enum Dropdown {
        HIERARCHY_LEVEL("Hierarchy level:"),
        KPI("KPIs:"),
        CURRENCY("Currency*:"),
        ENTITY_LEVEL("Entity level*:"),
        ACCOUNT_STRUCTURE("Account structure*:"),
        SHOW_RESULTS_IN("Show results in*:"),
        SHOW_VALUES("Show values*:"),
        DECIMALS("Decimals*:"),

        //Advanced
        TYPE_OF_DATE("Type of date*:"),

        BASE_VERSION("Base version*:"),

        START_PERIOD_FUTURE("Start period future:"),

        COMPARE_VERSION("Compare version:");

        private final String dataAttrQaValue;

        Dropdown(@NotNull String dataAttrQaValue) {
            this.dataAttrQaValue = dataAttrQaValue;
        }

        @Override
        public String toString() {
            return dataAttrQaValue;
        }
    }

    @Getter
    public enum InputWithDragAndDrop {
        ACCOUNTS("Accounts:"),
        KPI("KPIs:");

        private final String value;

        InputWithDragAndDrop(@NotNull String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Getter
    public enum Level {
        // Hierarchy level
        LEVEL_1("Level 1"),
        LEVEL_9("Level 9");

        private final String text;

        Level(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return getText();
        }
    }

    @Getter
    public enum YearViews {
        CALENDAR_YEARS("Calendar years"),
        FISCAL_YEARS("Fiscal years");

        private final String text;

        YearViews(String name) {
            this.text = name;
        }

    }

    public enum Decimals {
        WITHOUT_DECIMALS("Without decimals"),
        WITH_1_DECIMAL("With 1 decimal"),
        WITH_2_DECIMALS("With 2 decimals");

        private final String text;

        Decimals(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return text;
        }
    }

    public enum AccountStructure {
        ALPHA("Alpha"),
        CASH_FLOW("Cash-Flow"),
        LOCAL_GAAP_GERMANY("Local-GAAP Germany"),
        KAGB("KAGB");

        private final String text;

        AccountStructure(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }
    }

    public enum Currency {
        PORTFOLIO("Portfolio Level"),
        COMPANY("Company Level"),
        ASSET("Asset Level"),
        CLIENT("Client Level"),
        TRANSACTION("Transaction Level");

        private final String text;

        Currency(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum EntityLevel {
        PORTFOLIO("Portfolio"),
        COMPANY("Company"),
        ASSET("Asset");

        private final String text;

        EntityLevel(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }
    }

    public enum DateFormat {
        YEARS_MONTHS("Years - Months"),
        YEARS_QUARTERS("Years - Quarters"),
        QUARTERS_MONTHS("Quarters - Months"),
        MONTH("Months");

        private final String text;

        DateFormat(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }
    }

    public enum ShowValues {
        K("in K"),
        M("in M"),
        NO_FORMAT("Without formatting");

        private final String text;

        ShowValues(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return text;
        }
    }

    public enum ComparisonViewsRadioButton {
        IN_COLUMNS("In columns"),
        IN_ROWS("In rows");

        private final String text;

        ComparisonViewsRadioButton(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    @Getter
    public enum CustomizationAdvancedGroup {
        SHOW_FUTURE("Show future"),
        SHOW_HISTORY("Show history"),
        COMPARE_WITH_OTHER_RESULTS("Compare with other results");

        private final String label;

        CustomizationAdvancedGroup(@NotNull String label) {
            this.label = label;
        }

    }

    @Getter
    public enum CustomizationInput {
        AMOUNT("Amount*:");

        private final String label;

        CustomizationInput(@NotNull String label) {
            this.label = label;
        }

    }
}
