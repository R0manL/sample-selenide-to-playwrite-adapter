package com.superit.smart.qa.ui.smartbox.storybook;

import com.superit.smart.qa.core.PWContextExtension;
import com.superit.smart.qa.ui.smartbox.data.PredTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.superit.smart.qa.core.smartbox.enums.MenuModule.DASHBOARD;
import static com.superit.smart.qa.core.smartbox.enums.MenuModule.MASTER_DATA;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.data.PredPlanningSessions.*;
import static com.superit.smart.qa.ui.smartbox.pages.AssetsPage.getAssetsPage;
import static com.superit.smart.qa.ui.smartbox.pages.SideBar.getSideBar;
import static com.superit.smart.qa.ui.smartbox.service.LoginTestService.loginAndOpen;

/**
 * Created by R0manL.
 */

@Tag("smartbox")
@Tag("ui")
@Tag("storybook")
@Tag("dashboard")
@ExtendWith(PWContextExtension.class)
class DashboardTests {

    @DisplayName("87400 Verify Check Planning session after page switch")
    @Tag("rl@test.com")
    @Test
    void verifyCheckPlanningSessionAfterPageSwitch() {
        final String PRED_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_DASHBOARD_MODULE.ITEM_REAL_ESTATE.getNAME();

        final String MD_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.getNAME();

        loginAndOpen(DASHBOARD);
        getSideBar().searchAndSelectItem(PRED_ITEM_NAME)
                .getHeaderRowPanel()
                .getPlanningSessionDropdownWithSingleSelectOption()
                .expandAndSelectIfNotSelectedBy(PLANNING_SESSION_ACCOUNTING);

        getSideBar()
                .open(MASTER_DATA)
                .searchAndSelectItem(MD_ITEM_NAME)
                .getHeaderRowPanel()
                .getPlanningSessionDropdownWithMultiSelectOptions()
                .expandAndSelectIfNotSelectedBy(PLANNING_SESSION_ACCOUNTING);

        getSideBar().open(DASHBOARD);
        getSideBar().searchAndSelectItem(PRED_ITEM_NAME);
        getAssetsPage()
                .getHeaderRowPanel()
                .getPlanningSessionDropdownWithSingleSelectOption()
                .getSelectedOption()
                .shouldHas(text(PLANNING_SESSION_ACCOUNTING));
    }
}

