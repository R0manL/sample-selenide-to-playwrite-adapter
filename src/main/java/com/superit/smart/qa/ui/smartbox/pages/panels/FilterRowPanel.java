package com.superit.smart.qa.ui.smartbox.pages.panels;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.DashboardSettings;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.menus.FilterMenu;
import com.superit.smart.qa.ui.smartbox.pages.modals.*;
import com.superit.smart.qa.ui.smartbox.pages.widgets.DashboardMapViewWidget;
import com.superit.smart.qa.ui.smartbox.pages.widgets.DashboardTableViewWidget;
import com.superit.smart.qa.ui.smartbox.pages.widgets.DashboardTileViewWidget;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.*;
import static com.superit.smart.qa.utils.WaitUtils.*;

@Slf4j
public class FilterRowPanel {
    private static final WebElm FILTER_PANEL_ROOT_ELM = $(".filter-row");
    private static final WebElm TEXT_ELM_WITH_NUMBER_OF_ASSETS = FILTER_PANEL_ROOT_ELM.$(".filter-list .properties-value");

    public FilterRowPanel clickSingleEntityView() {
        log.info("Select 'single entity view'.");
        clickAndWaitTillBtnBeActive(SINGLE_ENTITY_VIEW);
        return this;
    }

    public CreateEntityWizard clickCreateEntity() {
        $("[data-attr-qa='wizard-createNewEntity-btn']").click();

        return new CreateEntityWizard();
    }

    public DashboardMapViewWidget clickMapView() {
        clickAndWaitTillBtnBeActive(MAP_VIEW);
        return DashboardMapViewWidget.getDashboardMapView();
    }

    public DashboardTileViewWidget clickTileView() {
        clickAndWaitTillBtnBeActive(TILES_VIEW);
        return new DashboardTileViewWidget();
    }

    public DashboardTableViewWidget clickTableView() {
        clickAndWaitTillBtnBeActive(TABLE_VIEW);
        return DashboardTableViewWidget.getDashboardTableView();
    }

    public DashboardSettings clickSettings() {
        log.info("Click 'Settings'.");
        new ButtonWithIconWrapper(SORTING, FILTER_PANEL_ROOT_ELM).click();
        return new DashboardSettings();
    }

    public void clickCustomizeView() {
        log.info("Click 'Customize view'.");
        FILTER_PANEL_ROOT_ELM.$("[data-attr-qa='settingMenu-show-btn']").filterByVisibilityIs(true).click();
        //Note. 'Customize view' button on some pages navigate to the widget, on other open dropdown with view selection.
    }

    public TutorialsModal clickOpenTutorialModule() {
        log.info("Click 'Open Tutorials Module'.");
        new ButtonWithIconWrapper(TUTORIALS, FILTER_PANEL_ROOT_ELM).click();
        return new TutorialsModal();
    }

    public FilterMenu clickFilter() {
        waitOnLoadersDisappearAndCheckErrors();
        WebElm filterButton = new ButtonWithIconWrapper(FILTER, FILTER_PANEL_ROOT_ELM).getWebElm();

        filterButton.click();
        sleepForWebElmRefreshDuration();

        FilterMenu result = new FilterMenu();
        WebElm leftFilterMenuTitle = result.getMenuTitleElm();

        if (!leftFilterMenuTitle.isDisplayed()) {
            log.warn("Left filter panel has not opened from the first time, try to open ones again.");
            filterButton.click();
            leftFilterMenuTitle.shouldBe(visible);
        }

        return new FilterMenu();
    }

    public WebElm getNumOfDisplayedAssetsElm() {
        return TEXT_ELM_WITH_NUMBER_OF_ASSETS;
    }

    public int getNumberOfEntitiesGreaterThenZero() {
        log.info("Get Number Of Entities");

        return waitUntilOwnTextBeginNotFromZeroAndGetNumberValue(getNumOfDisplayedAssetsElm());
    }

    public WebElm getSaveChangesButton() {
        return $("button.save-value-bar_actions-save");
    }

    public FilterRowPanel clickSaveChanges() {
        getSaveChangesButton().click();
        getSaveChangesButton().shouldBe(disappear);
        sleepForWebElmRefreshDuration(8);
        waitOnLoadersDisappearAndCheckErrors();

        return new FilterRowPanel();
    }

    /**
     * Click at button e.g. admin view, single entity view
     * button - web element that has 'is-active' state.
     **/
    public static void clickAndWaitTillBtnBeActive(ButtonWithIconWrapper.IconClassName button) {
        log.debug("Click and wait to become active button: '{}'.", button.getClassName());

        ButtonWithIconWrapper btn = new ButtonWithIconWrapper(button, FILTER_PANEL_ROOT_ELM);
        btn.getWebElm().shouldBe(visible);
        $("a.is-blue.mat-button-disabled").shouldBe(noneVisible());

        final long WAIT_TIME_FOR_ITERATION = ENV_CONFIG.webElmRefreshDuration().toMillis();
        final long MAX_ATTEMPTS = ENV_CONFIG.webElmLoadDuration().multipliedBy(4).toMillis() / WAIT_TIME_FOR_ITERATION;

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            sleep(WAIT_TIME_FOR_ITERATION);

            if (!btn.isActive()) {
                btn.click();
            } else {
                break;
            }
        }

        btn.waitToBecomeActive();
    }

    public class ComparisonSubPanel {
        private final WebElm panelRootElm = $("form.filter-list");

        public WebElm getPeriodsElms() {
            expandPeriodSelectDropdown();

            return $$(".mat-select-panel-wrap mat-option .value-name");
        }

        public FilterRowPanel clickResetToDefault() {
            panelRootElm.$("button .icon-refresh").click();

            return new FilterRowPanel();
        }

        private void expandPeriodSelectDropdown() {
            panelRootElm.$("mat-select").click();
        }
    }
}