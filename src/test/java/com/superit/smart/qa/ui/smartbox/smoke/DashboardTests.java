package com.superit.smart.qa.ui.smartbox.smoke;

import com.superit.smart.qa.core.PWContextExtension;
import com.superit.smart.qa.ui.smartbox.data.PredTestData;
import com.superit.smart.qa.ui.smartbox.pages.AssetsPage;
import com.superit.smart.qa.ui.smartbox.pages.components.dropdown.CustomizeViewDropdown;
import com.superit.smart.qa.ui.smartbox.pages.panels.FilterRowPanel;
import com.superit.smart.qa.ui.smartbox.pages.panels.HeaderRowPanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.superit.smart.qa.core.smartbox.enums.MenuModule.DASHBOARD;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visibleAtLeastOne;
import static com.superit.smart.qa.ui.smartbox.data.PredPlanningSessions.PLANNING_SESSION_ACCOUNTING;
import static com.superit.smart.qa.ui.smartbox.pages.DashboardCustomizationPage.getDashboardCustomizationPage;
import static com.superit.smart.qa.ui.smartbox.pages.SideBar.getSideBar;
import static com.superit.smart.qa.ui.smartbox.service.LoginTestService.loginAndOpen;

/**
 * Created by R0manL.
 */
@Tag("smartbox")
@Tag("ui")
@Tag("smoke")
@Tag("dashboard")
@ExtendWith(PWContextExtension.class)
class DashboardTests {

    @DisplayName("Verify Real estate > Tile / Table / Map views.")
    @Tag("rl@test")
    @Test
    void verifyDashboardRealEstateTileTableMapViews() {
        final String PRED_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_DASHBOARD_MODULE.ITEM_REAL_ESTATE.getNAME();

        loginAndOpen(DASHBOARD);

        AssetsPage assetsPage = getSideBar().searchAndSelectItem(PRED_ITEM_NAME);
        assetsPage
                .getHeaderRowPanel()
                .getPlanningSessionDropdownWithSingleSelectOption()
                .expandAndSelectIfNotSelectedBy(PLANNING_SESSION_ACCOUNTING);

        FilterRowPanel filterRowPanel = assetsPage.getFilterRowPanel();

        filterRowPanel.clickCustomizeView();
        new CustomizeViewDropdown().clickSetupView();
        getDashboardCustomizationPage().clickBackToDashboard();

        filterRowPanel.clickTileView().getFirstTileElm().shouldBe(visible);
        filterRowPanel.clickTableView().getDashboardLeftSection().shouldBe(visible);
        filterRowPanel.clickMapView().getMapViewRootElement().shouldBe(visible);
    }

    @DisplayName("Verify Dashboard > Asset overview > Settings, Customize view, Tutorials module.")
    @Tag("rl@test")
    @Test
    void verifyDashboardAssetOverview() {
        final String PRED_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_DASHBOARD_MODULE.ITEM_REAL_ESTATE.getNAME();

        loginAndOpen(DASHBOARD);

        AssetsPage page = getSideBar().searchAndSelectItem(PRED_ITEM_NAME);
        FilterRowPanel filterRowPanel = page.getFilterRowPanel();

        filterRowPanel
                .clickSettings()
                .clickClosePanel();

        filterRowPanel.clickCustomizeView();

        filterRowPanel.clickOpenTutorialModule().clickClose();

        HeaderRowPanel headerPanel = page.getHeaderRowPanel();

        String selectedSession = headerPanel
                .getPlanningSessionDropdownWithSingleSelectOption()
                .getSelectedOption()
                .getText();

        headerPanel
                .getPlanningSessionDropdownWithSingleSelectOption()
                .expandAndSelectIfNotSelectedBy(selectedSession);

        headerPanel
                .expandAndOpenTopListSettings()
                .getTopListsElms()
                .shouldHave(visibleAtLeastOne);
    }
}
