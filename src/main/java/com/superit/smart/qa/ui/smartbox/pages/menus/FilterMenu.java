package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.TopListSettingsPage;
import com.superit.smart.qa.ui.smartbox.pages.components.AdvancedFilter;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.link.LinkEmWithTextWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.TopListSettingsPage.getTopListSettingsPage;
import static com.superit.smart.qa.ui.smartbox.pages.components.AdvancedFilter.getAdvancedFilter;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.RESET_ALL_FILTERS;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.USE_FILTERS_TO_CREATE_A_NEW_TOP_LIST;
import static com.superit.smart.qa.ui.smartbox.pages.components.link.LinkEmWithTextWrapper.LinkText.CLEAR;
import static com.superit.smart.qa.utils.StringUtils.getMatchedText;
import static com.superit.smart.qa.utils.WaitUtils.*;

@Slf4j
public class FilterMenu {
    private static final WebElm ROOT_ELM = $(".app-aside.is-show");
    private static final WebElm SECTION_EXPANDED_ELM = ROOT_ELM.$(".app-aside-child-content.is-show");
    private static final WebElm ACTIVE_DROPDOWN_ROOT_ELM = ROOT_ELM.$(".dropdown-content.is-show");
    private static final WebElm ACTIVE_DROPDOWN_SEARCH_INPUT = ACTIVE_DROPDOWN_ROOT_ELM.$(".dropdown-search input");
    private static final WebElm ACTIVE_DROPDOWN_OPTION_ELMS = ACTIVE_DROPDOWN_ROOT_ELM.$(".mat-option-text");
    private static final WebElm SEE_RESULTS_BTN = ROOT_ELM.$("#asideBottomBtn button.control-it-filter-menu");

    public FilterMenu collapseActiveDropdown() {
        getMenuTitleElm().click();
        ACTIVE_DROPDOWN_ROOT_ELM.should(disappear);

        return this;
    }

    public void collapseActiveDropdownIfDisplayed() {
        if (ACTIVE_DROPDOWN_ROOT_ELM.isDisplayed()) {
            collapseActiveDropdown();
        }
    }

    public void clickSeeResults() {
        sleepForWebElmRefreshDuration(); //Note. When click at 'see result' before num of items recalculation, old value has been shown.
        SEE_RESULTS_BTN.click();
        ROOT_ELM.should(disappear, ENV_CONFIG.pageLoadDuration());
        waitOnLoadersDisappearAndCheckErrors();
    }

    public WebElm getSeeResultsBtn() {
        return SEE_RESULTS_BTN;
    }

    public FilterMenu expandDropdown(@NotNull Dropdown dropdown) {
        getDropdownInputFor(dropdown).click();
        waitOnRoundProgressSpinnerMightAppearThenDisappear(ACTIVE_DROPDOWN_ROOT_ELM);

        return this;
    }

    public WebElm getActiveDropdownOptionsElms() {
        return ACTIVE_DROPDOWN_OPTION_ELMS;
    }

    public WebElm getActiveDropdownElmByText(@NotNull String text) {
        return getActiveDropdownOptionsElms()
                .getByText(text);
    }

    public WebElm getDropdownInputFor(Dropdown dropdown) {
        return getInputBy(dropdown.getTitle());
    }

    public FilterMenu search(@NotNull String value) {
        getSearchField().val(value);
        waitOnProgressBarMightAppearThenDisappear(ROOT_ELM);

        return this;
    }

    public WebElm getSearchField() {
        return ACTIVE_DROPDOWN_SEARCH_INPUT;
    }


    public WebElm getInputBy(@NotNull String caption) {
        log.info("Get input by caption: '{}'.", caption);
        return getSectionContainerFor(caption)
                .$("input")
                .filterByVisibilityIs(true)
                .first();     //Note. label has not linked with input, we must take first one.
    }

    private WebElm getSectionContainerFor(@NotNull String sectionName) {
        return ROOT_ELM.$(".app-aside-sidebar-items")
                .filterByChild("//label[starts-with(normalize-space(.), '" + sectionName + "')]/..");
    }

    public WebElm getFilterGroup(FilterGroup filterGroup) {
        return ROOT_ELM.$(".fm-head-title").filterByText(filterGroup.getName());
    }

    public WebElm getMenuTitleElm() {
        return ROOT_ELM.$(".widget-title-head");
    }


    @Getter
    public enum Dropdown {
        ASSET("Asset"),
        ASSET_ID("Asset ID"),
        ASSET_TYPE("Asset type"),
        bITY("bITy"),
        CONDITION_ID("Condition ID"),
        COMPANY_ID("Company ID"),
        CONTRACT_ID("Contract ID"),
        CONTRACT_PARTNER("Contract Partner"),
        CONTRACT_TYPE("Contract type"),
        FUND_TYPE("Fund type"),
        ID("ID"),
        KPI("KPI"),
        LEASE_ID("Lease ID"),
        LOAN_ID("Loan ID"),
        OPERATOR("Operator"),
        PORTFOLIO_ID("Portfolio ID"),
        SHORT_DESIGNATION("Short designation"),
        START_FISCAL_YEAR("Start fiscal year (FY)"),
        TYPE("Type"),
        TYPE_OF_USE("Type of use"),
        UNIT_ID("Unit ID"),
        VACANCY_CONTRACT("Vacancy contract"),
        VAT_LIABLE("VAT liable"),
        YEAR_OF_CONSTRUCTION("Year of construction");

        private final String title;

        Dropdown(@NotNull String title) {
            this.title = title;
        }
    }

    public enum DropdownValue {
        TRUE("True"),
        FALSE("False");

        private final String title;

        DropdownValue(@NotNull String title) {
            this.title = title;
        }

        public String getValue() {
            return this.title;
        }
    }

    public enum Input {
        EARLIEST_POSSIBLE_END_DATE("Earliest possible end date"),
        START("Start");

        private final String title;

        Input(@NotNull String title) {
            this.title = title;
        }

        @NotNull
        public String getName() {
            return this.title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum DatePicker {
        ACQ_ECONOMIC_TRANSITION_DATE("Acq. economic transition date");

        private final String title;

        DatePicker(@NotNull String title) {
            this.title = title;
        }

        @NotNull
        public String getName() {
            return this.title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum FilterGroup {
        BUILDINGS("LAND & BUILDINGS"),
        CONDITIONS("Contract Conditions"),
        CONTRACTS("Contracts"),
        LEASES("LEASE CONTRACTS"),
        LOANS("LOANS"),
        MARKET_VALUE_APPRAISALS("MARKET VALUE APPRAISALS"),
        PORTFOLIOS("FUNDS | PORTFOLIOS | MANDATES"),
        REAL_ESTATE_ASSETS("REAL ESTATE ASSETS"),
        UNITS("RENTAL UNITS");

        private final String title;

        FilterGroup(@NotNull String title) {
            this.title = title;
        }

        @NotNull
        public String getName() {
            return this.title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
