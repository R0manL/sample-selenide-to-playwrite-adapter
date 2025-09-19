package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterOperator;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.size;

@Slf4j
public class AdvancedFilter {
    private static final WebElm ROOT_ELM = $("control-it-filter-advanced-fields");
    private static final WebElm OPERATOR_DROPDOWN_SELECT = ROOT_ELM.$("//*[@class='field-wrapper' and " +
            ".//*[normalize-space(text())='Operator']]//mat-select");
    private static final WebElm ADVANCED_FILTER_ROOT_ELM = ROOT_ELM.$(".advanced-filter");
    private static final String INPUT_VALUE_CSS_LOCATOR = "mat-form-field input:not([hidden])";


    private AdvancedFilter() {
    }

    public static AdvancedFilter getAdvancedFilter() {
        return new AdvancedFilter();
    }

    public void selectOperator(TopFilterOperator topFilterOperator) {
        new DropdownWrapper(OPERATOR_DROPDOWN_SELECT)
                .expand()
                .scrollAndSelectFirstByText(topFilterOperator.getName());
    }

    public void selectValues(@NotNull String... values) {
        List<WebElm> inputElms = ADVANCED_FILTER_ROOT_ELM.$$(INPUT_VALUE_CSS_LOCATOR)
                .shouldHas(size(values.length)).all();

        int i = 0;
        for(WebElm input : inputElms) {
            input.sendKeys(values[i++]);
        }
    }

    public void selectOperatorWithValues(TopFilterOperator topFilterOperator, @NotNull String... values) {
        selectOperator(topFilterOperator);
        selectValues(values);
    }

    public String getSelectedOperatorValue() {
        return new DropdownWrapper(OPERATOR_DROPDOWN_SELECT).getSelectedOptionText();
    }

    public List<String> getSelectedValues() {
        log.info("Getting selected values");
        return ADVANCED_FILTER_ROOT_ELM.$$(INPUT_VALUE_CSS_LOCATOR).values();
    }
}