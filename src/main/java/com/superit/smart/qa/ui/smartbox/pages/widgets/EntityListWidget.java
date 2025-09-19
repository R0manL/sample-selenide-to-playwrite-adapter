package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.list.ListOfElmsWithScroller;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.AssetsPage.getAssetsPage;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;


@Slf4j
public class EntityListWidget {
    private static final WebElm WIDGET_ROOT_ELM = $("#table-wrap");
    private static final InputWithPlaceholderWrapper SEARCH_INPUT_WRAPPER = new InputWithPlaceholderWrapper(InputPlaceholder.SEARCH, WIDGET_ROOT_ELM);
    private static final String ENTITY_CSS_SELECTOR = "control-it-multi-row";
    private static final String ENTITY_CSS_SELECTOR_TEMPLATE = "[role='row'][row-index='%s'] " + ENTITY_CSS_SELECTOR;
    private static final String TITLE_CSS_SELECTOR = ".multi-row-header-title";
    private static final String VALUE_CSS_SELECTOR = ".multi-row-value";
    private static final String PROP_FIN_STATUS_CSS_SELECTOR = ".property-financial-status";

    public EntityListWidget() {
        WIDGET_ROOT_ELM.shouldBe(visible, ENV_CONFIG.pageLoadDuration());
    }

    public WebElm getRootElm() {
        return WIDGET_ROOT_ELM;
    }

    public EntityListWidget search(@NotNull String text) {
        waitOnLoadersDisappearAndCheckErrors(WIDGET_ROOT_ELM); // Note. Search results may be invalid when search during loading.
        SEARCH_INPUT_WRAPPER.search(text);

        return this;
    }

    public InputWithPlaceholderWrapper getSearchInputWrapper() { return SEARCH_INPUT_WRAPPER; }

    public WebElm getSearchInput() {
        return SEARCH_INPUT_WRAPPER.getInput();
    }

    public WebElm getEntities() {
        return WIDGET_ROOT_ELM.$(ENTITY_CSS_SELECTOR);
    }

    public WebElm getTitles() {
        return WIDGET_ROOT_ELM.$(TITLE_CSS_SELECTOR);
    }

    public WebElm getValues() {
        return WIDGET_ROOT_ELM.$(VALUE_CSS_SELECTOR);
    }

    public WebElm getEntityByTitle(@NotNull String title) {
        return getTitles().filterByText(title).shouldBe(single());
    }

    public WebElm getEntityByValue(@NotNull String value) {
        return  getValues().filterByText(value).shouldBe(single());
    }

    public WebElm getTitleBy(@NotNull String text) {
        return getTitles().getByText(text);
    }

    public WebElm getTitleBy(int rowIndex) {
        return getEntityElmBy(rowIndex).$(TITLE_CSS_SELECTOR);
    }

    public List<String> getTitlesTexts() {
        return getTextFrom(TITLE_CSS_SELECTOR);
    }
    /* Use with more than 20 elements in the list, this method allows to scroll and take all Values from Entity List that uses lazy loading */

    public List<String> getValuesTexts() {
        return getTextFrom(VALUE_CSS_SELECTOR);
    }

    public WebElm getPropertyFinancialStatusFor(WebElm entity) {
        return entity.$(PROP_FIN_STATUS_CSS_SELECTOR);
    }

    public void selectByTitle(@NotNull String title) {
        getEntityByTitle(title).click();
    }

    public void selectByValue(@NotNull String value) {
        getEntityByValue(value)
                .getByText(value)
                .shouldBe(visibleAtLeastOne) //Note. Some entities have 2 values: description and nameEn. Select by decr.
                .get(0)
                .click();
    }

    public void scrollAndSelectFirstByValue(@NotNull String value) {
        WebElm entities = getEntities();
        new ListOfElmsWithScroller(entities).findFirstChildBy(value);
    }
    public EntityListWidget searchAndSelectByTitle(@NotNull String title) {
        log.info("Search and select by title : '{}'.", title);
        search(title);
        selectByTitle(title);

        return this;
    }

    public EntityListWidget searchAndSelectByValue(@NotNull String value) {
        log.info("Search and select by value : '{}'.", value);
        search(value);
        selectByValue(value);

        return this;
    }

    /* Use with more than 20 elements in the list, this method allows to scroll and take all Titles from Entity List that uses lazy loading */
    /**
     * @param titleOrValueCssSelector - css class for title or value of Entity from the List
     * @return List of Strings (titles or values)
     */
    private List<String> getTextFrom(@NotNull String titleOrValueCssSelector) {
        int numberOfEntities = getAssetsPage().getFilterRowPanel().getNumberOfEntitiesGreaterThenZero();
        List<String> result = new ArrayList<>();
        // scroll down and add cell title to the list
        IntStream
                .range(0, numberOfEntities)
                .forEach(i -> {
                    String title = getEntityElmBy(i)
                            .scrollIntoView()
                            .shouldBe(visible, ENV_CONFIG.pageLoadDuration())
                            .$(titleOrValueCssSelector)
                            .getText();

                    result.add(title);
        });

        return result;
    }

    private WebElm getEntityElmBy(int rowIndex) {
        return  WIDGET_ROOT_ELM.$(String.format(ENTITY_CSS_SELECTOR_TEMPLATE, rowIndex));
    }
}