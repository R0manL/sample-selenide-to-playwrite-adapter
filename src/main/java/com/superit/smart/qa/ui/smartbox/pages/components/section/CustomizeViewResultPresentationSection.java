package com.superit.smart.qa.ui.smartbox.pages.components.section;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder.SEARCH;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CUSTOMIZE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.APPLY_AND_SHOW_RESULTS;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class CustomizeViewResultPresentationSection {
    @Getter private static final WebElm ROOT_ELEMENT = $("control-it-side-customization");
    private static final WebElm CUSTOMIZATION_LIST_ROOT_ELM = ROOT_ELEMENT.$(".list-scroll-container");
    private static final WebElm CUSTOMIZATIONS = CUSTOMIZATION_LIST_ROOT_ELM.$("control-it-side-customization-list-item");

    private static final String FIELD_XPATH_TEMPLATE = "//*[contains(@class,'block-item')]//*[normalize-space(.)='%s']/..";


    public WebElm getRootElement() {
        return ROOT_ELEMENT;
    }

    public CustomizeViewResultPresentationSection clickSetupView() {
        log.info("Click setup views.");
        new ButtonWithIconWrapper(CUSTOMIZE, ROOT_ELEMENT).click();
        ROOT_ELEMENT.should(disappear);

        return this;
    }

    public CustomizeViewResultPresentationSection search(@NotNull String name) {
        waitOnLoadersDisappearAndCheckErrors(); //Note. If search before page has loaded, page may refresh and we may select wrong item.

        new InputWithPlaceholderWrapper(SEARCH, ROOT_ELEMENT)
                .clear()
                .sendKeys(name);

        waitOnLoadersDisappearAndCheckErrors();

        return this;
    }

    public void select(@NotNull String name) {
        get(name).click();
    }

    public CustomizeViewResultPresentationSection clickApplyView() {
        new ButtonWithTextWrapper(APPLY_AND_SHOW_RESULTS, ROOT_ELEMENT).click();
        return this;
    }

    public void setInput(@NotNull String value, Input input) {
        WebElm inputElm = getInput(input);
        setInput(value, inputElm);
    }

    public void selectDropdownOption(@NotNull String value, Dropdown dropdown) {
        DropdownWithSearchWrapper dropdownWrapper = getDropdownWithSearchWrapper(dropdown.getCaption()).expand();

        if (dropdownWrapper.getSearchElm().isDisplayed()) {
            dropdownWrapper.searchThenSelect(value);
        } else {
            dropdownWrapper.scrollAndSelectFirstByText(value);
        }
    }

    public void setCheckbox(boolean value, CheckBox checkBox) {
        WebElm label = ROOT_ELEMENT.$("//label[.//*[normalize-space(text())='" + checkBox.getCaption() + "']]")
                .filterByVisibilityIs(true);

        if (value ^ label.$("input").hasAttribute("aria-checked", "true")) {
            label.click();
        } else {
            log.warn("Checkbox: {}, has already set. Skip.", checkBox.getCaption());
        }
    }



    public WebElm get(@NotNull String name) {
        return CUSTOMIZATIONS.filterByChild("//*[normalize-space(text())='" + name +
                "' and contains(@class, 'text-title')]");
    }

    public void searchSelectAndApplyPresentationViewIfNotSelected(@NotNull String name) {
        search(name);

        if (!hasSelected(name)) {
            select(name);
            clickApplyView();
            ROOT_ELEMENT.should(disappear);
        } else {
            log.warn("Customize view: '{}', has already selected. Skip this step.", name);
            collapse();
        }
    }

    public void collapse() {
        new ButtonWithIconWrapper(CLOSE, ROOT_ELEMENT.$("control-it-modal-head")).click();
        ROOT_ELEMENT.should(disappear);
    }

    public boolean hasSelected(@NotNull String name) {
        return get(name).$(".text-title +img[alt='Applied']").isDisplayed();
    }

    public void selectPresentationViewIfNotSelected(@NotNull String name) {
        if (!hasSelected(name)) {
            select(name);
        } else {
            log.warn("Customize view: '{}', has already selected. Skip this step.", name);
        }
    }


    private WebElm getField(@NotNull String caption) {
        return ROOT_ELEMENT.$(String.format(FIELD_XPATH_TEMPLATE, caption)).filterByVisibilityIs(true);
    }

    private WebElm getInput(Input input) {
        return getField(input.getCaption()).$("input");
    }

    private void setInput(@NotNull String value, WebElm input) {
        new InputWrapper(input)
                .clear()
                .sendKeys(value);
    }

    private DropdownWithSearchWrapper getDropdownWithSearchWrapper(@NotNull String caption) {
        WebElm input = getField(caption).$("mat-select");
        return new DropdownWithSearchWrapper(input);
    }


    @Getter
    public enum Input {
        AMOUNT("Amount*:");

        private final String caption;

        Input(@NotNull String value) {
            this.caption = value;
        }
    }

    @Getter
    public enum Dropdown {
        BASE_VERSION("Base version*:"),
        TYPE_OF_DATE("Type of date*:"),
        START_PERIOD_FUTURE("Start period future:");

        private final String caption;

        Dropdown(@NotNull String value) {
            this.caption = value;
        }
    }

    @Getter
    public enum CheckBox {
        SHOW_HISTORY("Show history"),
        SHOW_FUTURE("Show future");

        private final String caption;

        CheckBox(@NotNull String value) {
            this.caption = value;
        }
    }
}
