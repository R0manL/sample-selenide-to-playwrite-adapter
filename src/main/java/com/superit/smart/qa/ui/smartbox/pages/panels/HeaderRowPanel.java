package com.superit.smart.qa.ui.smartbox.pages.panels;


import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import com.superit.smart.qa.ui.smartbox.pages.TopListSettingsPage;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithMultiSelectWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.ui.smartbox.pages.TopListSettingsPage.getTopListSettingsPage;
import static com.superit.smart.qa.utils.WaitUtils.*;


@Slf4j
public class HeaderRowPanel {
    private static final WebElm PANEL_ROOT_ELM = $("div.header-row");
    private static final String TOP_FILTER_LIST_SETTINGS_LINK_TEXT = "List Settings";
    private static final WebElm TOP_FILTER_DROPDOWN_ROOT_ELM = PANEL_ROOT_ELM.$("control-it-top-filter");

    public DropdownWithMultiSelectWrapper getPlanningSessionDropdownWithMultiSelectOptions() {
        WebElm selectElm = PANEL_ROOT_ELM.$("control-it-version-select");
        return new DropdownWithMultiSelectWrapper(selectElm);
    }

    public DropdownWrapper getPlanningSessionDropdownWithSingleSelectOption() {
        WebElm selectElm = PANEL_ROOT_ELM.$("control-it-version-select");
        return new DropdownWrapper(selectElm);
    }

    public DropdownWithSearchWrapper getTopFilterDropdown() {
        WebElm selectElm = TOP_FILTER_DROPDOWN_ROOT_ELM.$("mat-select");
        return new DropdownWithSearchWrapper(selectElm);
    }

    public HeaderRowPanel expandSearchAndSelectTopFilterIfNotSelectedBy(@NotNull String name) {
        return expandSearchAndSelectTopFilterIfNotSelectedBy(name, false);
    }

    public HeaderRowPanel expandSearchAndSelectTopFilterIfNotSelectedBy(@NotNull String name, boolean exactMatch) {
        log.info("Select '{}' top filter.", name);
        waitOnLoadersDisappearAndCheckErrors();
        WebElm selectedTopFilter = getTopFilterDropdown().getSelectedOption().shouldBe(Condition.notEmpty);

        if (!selectedTopFilter.getText().equals(name)) {
            DropdownWithSearchWrapper dropdown = expandAndWaitTillTopFiltersHasLoaded();

            if (exactMatch) {
                dropdown.searchThenSelectByExactText(name);
            } else {
                dropdown.searchThenSelect(name);
            }
            waitOnLoadersDisappearAndCheckErrors();
        } else {
            log.info("Top filter {} is already selected. Skip step", name);
        }

        return this;
    }

    public TopListSettingsPage expandAndOpenTopListSettings() {
        getTopFilterDropdown().expand();
        openTopListSettings();

        return getTopListSettingsPage();
    }

    public TopListSettingsPage openTopListSettings() {
        getTopFilterDropdown().scrollAndSelectFirstByText(TOP_FILTER_LIST_SETTINGS_LINK_TEXT);

        return getTopListSettingsPage();
    }

    private DropdownWithSearchWrapper expandAndWaitTillTopFiltersHasLoaded() {
        DropdownWithSearchWrapper result = getTopFilterDropdown().expand();
        waitOnSpinnerDisappear($(".top-filter-select"), ENV_CONFIG.webElmLoadDuration().multipliedBy(3));

        return result;
    }
}