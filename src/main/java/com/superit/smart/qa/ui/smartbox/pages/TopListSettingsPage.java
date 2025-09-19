package com.superit.smart.qa.ui.smartbox.pages;


import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterCriteria;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterLevel;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterOperator;
import com.superit.smart.qa.ui.smartbox.pages.components.Spinner;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.panels.ListDetailsPanel;
import com.superit.smart.qa.ui.smartbox.pojo.TopFilterUI;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import com.microsoft.playwright.Locator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.ADD;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.DELETE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.CONFIRM;
import static com.superit.smart.qa.ui.smartbox.pages.panels.ListDetailsPanel.getListDetailsMenu;
import static com.superit.smart.qa.utils.WaitUtils.*;

@Slf4j
public class TopListSettingsPage {
    private static final WebElm PAGE_ROOT_ELM = $("control-it-property-list-settings");

    private static final WebElm MENU_SECTION_ELM = PAGE_ROOT_ELM.$(".filter-row");
    private static final WebElm SAVE_BTN = MENU_SECTION_ELM.$("button:not([disabled='true']) .icon-checked");
    private static final WebElm GROUP_LIST_ROOT_ELM = PAGE_ROOT_ELM.$(".cockpit-group-list");
    private static final WebElm TOP_FILTERS_ELMS = GROUP_LIST_ROOT_ELM.$$(".cockpit-group-item");
    private static final WebElm TOP_FILTER_ROW_ELMS = PAGE_ROOT_ELM.$$("control-it-list-filters-item");

    private static final String DROPDOWN_XPATH_TEMPLATE = "[descendant::control-it-dropdown//span[normalize-space(text())='%s']]";

    // Value fields
    private static final String VALUE_DROPDOWN_MULTI_SELECT_CSS_LOCATOR = "control-it-multiple-values control-it-autocomplete-infinite input[type='text']";
    private static final String VALUE_DROPDOWN_MULTI_SELECT_CSS_LOCATOR_2 = "control-it-global-variable-field .mat-select-value-text";
    private static final String VALUE_INPUT_XPATH_LOCATOR = "//label/*[normalize-space(text())='Value:*']/../..//input";
    private static final String VALUE_INPUT_CSS_LOCATOR = "control-it-multiple-values input[matinput]:not([hidden])";
    private static final String VALUE_INPUT_2_CSS_LOCATOR = "mat-select[formcontrolname='value'] .mat-select-value";
    private static final String VALUE_INPUT_3_CSS_LOCATOR = "input[formcontrolname='value']";
    private static final String VALUE_DROPDOWN_SINGLE_SELECT_CSS_LOCATOR = "mat-select[formcontrolname='value'] .mat-select-value";
    private static final String VALUE_DROPDOWN_SINGLE_SELECT_2_CSS_LOCATOR = "control-it-autocomplete-infinite input[placeholder='Value']";
    private static final String DATE_PICKER_INPUT_CSS_LOCATOR = "control-it-global-variable-field input[smartitdatemask]";
    private static final String VALUE_DROPDOWN_ROOT_CSS_LOCATOR = ".dropdown-content.is-show";
    private static final String VALUE_OPTION_ELMS_CSS_LOCATOR = ".value-name";
    private static final String VALUE_DROPDOWN_2_ROOT_CSS_LOCATOR = "[role='listbox']";
    private static final String VALUE_OPTION_2_ELMS_CSS_LOCATOR = "mat-option[role='option']";


    private TopListSettingsPage() {
        // None
    }

    public static TopListSettingsPage getTopListSettingsPage() {
        return new TopListSettingsPage();
    }

    public List<WebElm> getFilters(@NotNull TopFilterUI topFilterUI) {
        List<WebElm> ruleElms = new ArrayList<>();
        topFilterUI.getListFilters().forEach(rule -> {
            WebElm ruleBaseElm = $("//control-it-list-filters//*[contains(@class, 'group-wrapper')]" +
                    String.format(DROPDOWN_XPATH_TEMPLATE, rule.getLevel()) +
                    String.format(DROPDOWN_XPATH_TEMPLATE, rule.getCriteria()) +
                    String.format(DROPDOWN_XPATH_TEMPLATE, rule.getOperator()));

            WebElm ruleElm;
            switch (rule.getValues().size()) {
                case 0:
                    log.info("Expected top filter does not have rules");
                    ruleElm = ruleBaseElm;
                    break;
                case 1:
                    log.info("Expected top filter has one rule");
                    String value = rule.getValues().get(0);
                    ruleElm = ruleBaseElm.$("//*[descendant::control-it-multiple-values//mat-chip[normalize-space(text())='" + value + "']]");
                    break;
                case 2:
                    String firstValue = rule.getValues().get(0);
                    String secondValue = rule.getValues().get(1);
                    log.info("Expected top filter has two rules");
                    ruleElm = ruleBaseElm.$("//*[descendant::control-it-multiple-values//mat-chip[normalize-space(text())='" + firstValue + "']]" +
                            "[descendant::control-it-multiple-values//mat-chip[normalize-space(text())='" + secondValue + "']]");
                    break;
                default:
                    log.info("Expected top filter has 3 or more rules");
                    ruleElm = ruleBaseElm.$("//*[descendant::control-it-autocomplete-infinite//input[@placeholder=' (" + rule.getValues().size() + ") Selected']]");
            }
            ruleElms.add(ruleElm);
        });
        return ruleElms;
    }

    public WebElm getTopListsElms() {
        return TOP_FILTERS_ELMS;
    }

    public TopListSettingsPage addNewFilterWith(@NotNull String name) {
        clickAdd();
        setFilterNamesAndConfirmCreation(name, name);

        return this;
    }

    public TopListSettingsPage setFilterNamesAndConfirmCreation(@NotNull String enName, @NotNull String deName) {
        setFilterNames(enName, deName);
        confirmCreation();

        return this;
    }

    public TopListSettingsPage setFilterNames(@NotNull String enName, @NotNull String deName) {
        setInputValueOfCreationModule(CreationModalInput.LAST_NAME_EN, enName);
        setInputValueOfCreationModule(CreationModalInput.LAST_NAME_DE, deName);

        return this;
    }

    public void confirmCreation() {
        getConfirmButton().click();
        getConfirmButton().should(disappear);

        // Note: Delay required for the created filter to be downloaded and ready for rules adding.
        waitOnProgressBarMightAppearThenDisappear(PAGE_ROOT_ELM);
    }

    public WebElm getConfirmButton() {
        return new ButtonWithTextWrapper(CONFIRM, $("control-it-list-creation-modal .mat-dialog-actions"))
                .getWebElm();
    }

    public TopListSettingsPage selectTopFilter(@NotNull String name) {
        getTopFilterTitlesElms()
                .getLocator()
                .filter(new Locator.FilterOptions().setHasText(name))
                .first()
                .click();

        waitOnProgressBarMightAppearThenDisappear(getLastFilterRowRoot());

        return this;
    }

    public TopListSettingsPage searchTopFilter(@NotNull String name) {
        new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, GROUP_LIST_ROOT_ELM).sendKeys(name);

        return this;
    }

    public ListDetailsPanel openSettingsPanelFor(@NotNull String name) {
        waitOnLoadersDisappearAndCheckErrors();
        getTopListsElms().shouldHave(visibleAtLeastOne);

        getTopListsElms()
                .filterByText(name)
                .first()
                .$(ButtonWithIconWrapper.IconClassName.SETTINGS.getClassName())
                .click();

        return getListDetailsMenu();
    }

    public void clickAdd() {
        new ButtonWithIconWrapper(ADD, MENU_SECTION_ELM).click();
    }

    public void clickSave() {
        SAVE_BTN.click();
        waitOnProgressBarMightAppearThenDisappear(PAGE_ROOT_ELM);
        SAVE_BTN.shouldBe(hidden);
        sleepForWebElmRefreshDuration(2);
    }

    public DropdownWithSearchWrapper getDropdown(@NotNull Dropdown dropdown) {
        WebElm select = getFilterRowsRoots()
                .last()
                .$("//div[contains(@class,'field-item') and descendant::span[normalize-space(text())='" + dropdown + "']]" +
                        "//mat-select[@role='combobox']/..");

        return new DropdownWithSearchWrapper(select);
    }

    public TopListSettingsPage selectLevel(TopFilterLevel value) {
        expandDropdownAndSelect(getDropdown(Dropdown.LEVEL), value.toString());

        return this;
    }

    public TopListSettingsPage selectCriteria(TopFilterCriteria value) {
        expandDropdownAndSelect(getDropdown(Dropdown.CRITERIA), value.toString());

        return this;
    }

    public TopListSettingsPage selectOperator(TopFilterOperator value) {
        expandDropdownAndSelect(getDropdown(Dropdown.OPERATOR), value.toString());

        return this;
    }

    public TopListSettingsPage selectKPI(@NotNull String value) {
        expandDropdownSearchAndSelect(getDropdown(Dropdown.KPI), value);

        return this;
    }

    public void clickDeleteAndConfirm() {
        clickDeleteBtn();
        clickConfirmDeletion();
    }

    public TopListSettingsPage addOneMoreFilterRow() {
        getLastFilterRowRoot()
                .$("//ancestor::div[contains(@class,'group-wrapper')]")
                .$("//button[descendant::span[contains(@class, 'icon-plus')]]")
                .click();

        return this;
    }

    public TopListSettingsPage setValue(@NotNull String value) {
        // Set variable value.
        if (ValueDateVariable.isVariable(value)) {
            char variablePrefix = value.charAt(0);

            getLastFilterRowRoot().$(DATE_PICKER_INPUT_CSS_LOCATOR).sendKeys(String.valueOf((variablePrefix)));

            sleepForWebElmRefreshDuration();

            selectValueFromDropdown(value);
        } else {
            // Set string (text, date, number) value.
            sleepForWebElmRefreshDuration(); // Note. For a short period invalid value input has shown.
            WebElm input1 = getLastFilterRowRoot().$(VALUE_INPUT_XPATH_LOCATOR);
            WebElm input2 = getLastFilterRowRoot().$(DATE_PICKER_INPUT_CSS_LOCATOR);
            WebElm visibleInput = waitAndReturnFirstVisibleElmOf(input1, input2);

            visibleInput.sendKeys(value);
            visibleInput.pressEnter();
        }

        return TopListSettingsPage.this;
    }

    public TopListSettingsPage setValues(@NotNull String from, @NotNull String to) {
        WebElm inputElms = getLastFilterRowRoot().$(VALUE_INPUT_CSS_LOCATOR);

        if (!inputElms.first().isDisplayed()) {
            inputElms = getLastFilterRowRoot().$(DATE_PICKER_INPUT_CSS_LOCATOR);
        }

        inputElms.shouldHave(size(2));
        inputElms.all().get(0).sendKeys(from);
        inputElms.all().get(1).sendKeys(to);

        return this;
    }

    /**
     * Method detect type of input and wait till input's value will be equal to @text
     **/
    public WebElm checkValueShouldBe(@NotNull String text) {
        sleepForWebElmRefreshDuration(4);

        WebElm multiSelectInput = getLastFilterRowRoot().$(VALUE_DROPDOWN_MULTI_SELECT_CSS_LOCATOR);

        if (multiSelectInput.isDisplayed()) {
            return multiSelectInput.shouldHas(inputPlaceholderWith(Pattern.compile(text)));
        }

        WebElm globalVariableField = getLastFilterRowRoot().$(VALUE_DROPDOWN_MULTI_SELECT_CSS_LOCATOR_2);
        if (globalVariableField.isDisplayed()) {
            return globalVariableField.shouldHas(ownText(text));
        }

        WebElm singleSelectInput = getLastFilterRowRoot().$(VALUE_INPUT_CSS_LOCATOR);
        if (singleSelectInput.isDisplayed()) {
            return singleSelectInput.shouldHas(value(text));
        }

        WebElm singleSelectInput2 = getLastFilterRowRoot().$(VALUE_INPUT_2_CSS_LOCATOR);
        if (singleSelectInput2.isDisplayed()) {
            return singleSelectInput2.shouldHas(ownText(text));
        }

        WebElm singleSelectInput3 = getLastFilterRowRoot().$(VALUE_INPUT_3_CSS_LOCATOR);
        if (singleSelectInput3.isDisplayed()) {
            return singleSelectInput3.shouldHas(value(text));
        }

        WebElm datepickerInput = getLastFilterRowRoot().$(DATE_PICKER_INPUT_CSS_LOCATOR);
        if (datepickerInput.isDisplayed()) {
            return datepickerInput.shouldHas(value(text));
        }

        throw new IllegalStateException("Can't find value input element.");
    }

    public void openCalendarInValueField() {
        getLastFilterRowRoot()
                .$("control-it-global-variable-field mat-datepicker-toggle")
                .click();
    }

    public void closeDropdownOrPopup() {
        PAGE_ROOT_ELM.getActiveElement().pressEscape();
    }

    public WebElm getCalendarElmInValueField() {
        return $("mat-calendar.mat-calendar");
    }

    @NotNull
    public WebElm getValueSelectedOptionsElms() {
        return getLastFilterRowRoot().$("mat-chip");
    }

    public TopListSettingsPage selectMultipleValues(String... values) {
        Arrays.stream(values).forEach(value -> {
            WebElm searchInput = new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, getLastFilterRowRoot()).getInput();

            if (!searchInput.isDisplayed()) {
                expandValueMultiSelectDropdown();
            }

            if (!searchInput.getValue().isEmpty()) {
                searchInput.parent().$("button .icon-closed").click();
            }

            searchInput.sendKeys(value);

            $$(VALUE_OPTION_ELMS_CSS_LOCATOR)
                    .getLocator()
                    .filter(new Locator.FilterOptions().setHasText(value))
                    .first()
                    .click();
        });

        return this;
    }

    public TopListSettingsPage expandValueSingleSelectDropdown() {
        WebElm expandElm = getLastFilterRowRoot().$(VALUE_DROPDOWN_SINGLE_SELECT_CSS_LOCATOR);
        if (expandElm.isDisplayed()) {
            expandElm.click();
        } else {
            getLastFilterRowRoot().$(VALUE_DROPDOWN_SINGLE_SELECT_2_CSS_LOCATOR).click();
        }

        return this;
    }

    public TopListSettingsPage expandValueMultiSelectDropdown() {
        getLastFilterRowRoot()
                .$(VALUE_DROPDOWN_MULTI_SELECT_CSS_LOCATOR)
                .filterByVisibilityIs(true)
                .click();

        // Wait till dropdown will open and fully loaded.
        getValueMultipleSelectDropdown().shouldBe(visible);
        new Spinner().getRoundProgressSpinnerElm($(VALUE_DROPDOWN_ROOT_CSS_LOCATOR)).should(disappear);

        return this;
    }

    private WebElm getValueMultipleSelectDropdown() {
        return getLastFilterRowRoot().$(VALUE_DROPDOWN_ROOT_CSS_LOCATOR);
    }

    public TopListSettingsPage searchAndSelectValueFromDropdown(@NotNull String text) {
        searchInValueDropdown(text);
        return selectValueFromDropdown(text);
    }

    public TopListSettingsPage selectValueFromDropdown(@NotNull String option) {
        WebElm elms;

        if (getValueMultipleSelectDropdown().isDisplayed()) {
            elms = $$(VALUE_OPTION_ELMS_CSS_LOCATOR);
        } else {
            if ($(VALUE_DROPDOWN_2_ROOT_CSS_LOCATOR).isDisplayed()) {
                elms = $$(VALUE_OPTION_2_ELMS_CSS_LOCATOR);
            } else {
                elms = $$(VALUE_OPTION_ELMS_CSS_LOCATOR);
            }
        }

        elms.getByText(option).click();

        return this;
    }

    public WebElm getFilterRowsRoots() {
        return TOP_FILTER_ROW_ELMS;
    }


    public WebElm getFieldElm(CreationModalInput input) {
        return $("//*[contains(@class, 'field-item') " +
                "and .//*[normalize-space(text())='" + input + "']]");
    }

    public WebElm getInputFieldElm(CreationModalInput input) {
        return $(".field-item").andChildWithText(input.toString()).$("input");
    }

    public TopListSettingsPage clickHideTopList(@NotNull String name) {
        getTopListsElms()
                .filterByText(name)
                .$(".icon-opened-eye")
                .click();

        return this;
    }

    public TopListSettingsPage selectFilterList(TopFilterList name) {
        WebElm selectElm = MENU_SECTION_ELM.$("mat-select");
        DropdownWrapper dropdownWrapper = new DropdownWrapper(selectElm);
        dropdownWrapper.expandAndSelectIfNotSelectedBy(name.toString());

        return this;
    }

    private void clickConfirmDeletion() {
        $("control-it-confirm-modal").$("//button/*[normalize-space(text())='Delete']").click();
    }

    private WebElm getTopFilterTitlesElms() {
        return GROUP_LIST_ROOT_ELM.$$(".cockpit-group-title");
    }

    private WebElm getLastFilterRowRoot() {
        return getFilterRowsRoots()
                .shouldHave(visibleAtLeastOne)
                .last();
    }

    private void setInputValueOfCreationModule(CreationModalInput input, @NotNull String value) {
        log.info("Set input value '{}' to the field '{}' of creation mode", value, input);
        WebElm inputElm = getInputFieldElm(input);
        inputElm.val(value);
        inputElm.pressEnter();
    }

    private void searchInValueDropdown(@NotNull String text) {
        new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, getLastFilterRowRoot()).search(text);
    }

    private void clickDeleteBtn() {
        new ButtonWithIconWrapper(DELETE, PAGE_ROOT_ELM).click();
    }

    private void expandDropdownAndSelect(DropdownWithSearchWrapper dropdown, @NotNull String value) {
        dropdown
                .expand()
                .scrollAndSelectFirstByExactText(value);
    }

    private void expandDropdownSearchAndSelect(DropdownWithSearchWrapper dropdown, @NotNull String value) {
        dropdown
                .expand()
                .search(value)
                .scrollAndSelectFirstByExactText(value);
    }


    public enum Dropdown {
        LEVEL("Level:*"),
        CRITERIA("Criteria:*"),
        KPI("KPI:*"),
        OPERATOR("Operator:*"),
        VALUE("Value:*");

        private final String title;

        Dropdown(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum CreationModalInput {
        LAST_NAME_EN("List Name (EN)"),
        LAST_NAME_DE("List Name (DE)");

        private final String title;

        CreationModalInput(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    @Getter
    public enum ValueDateVariable {
        CURRENT_MONTH("@currentMonth");

        private final String value;

        ValueDateVariable(@NotNull String value) {
            this.value = value;
        }

        public static boolean isVariable(String value) {
            for (ValueDateVariable v : ValueDateVariable.values()) {
                if (v.getValue().equals(value)) {
                    return true;
                }
            }

            return false;
        }
    }

    public enum TopFilterList {
        ALL("All lists"),
        HIDDEN("Hidden Lists");

        private final String text;

        TopFilterList(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
