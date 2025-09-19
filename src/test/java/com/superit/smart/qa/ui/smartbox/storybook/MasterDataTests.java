package com.superit.smart.qa.ui.smartbox.storybook;

import com.superit.smart.qa.core.PWContextExtension;
import com.superit.smart.qa.ui.smartbox.data.*;
import com.superit.smart.qa.ui.smartbox.pages.*;
import com.superit.smart.qa.ui.smartbox.pages.modals.*;
import com.superit.smart.qa.ui.smartbox.pages.widgets.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.core.smartbox.enums.MenuModule.MASTER_DATA;
import static com.superit.smart.qa.ui.smartbox.data.PredAssignationOfMaintenanceCostApproachToStandardUse.VALXML_LEISURE_LEISURE;
import static com.superit.smart.qa.ui.smartbox.data.PredAssignationOfMaintenanceCostApproachToStandardUse.VALXML_OFFICE_OFFICE;
import static com.superit.smart.qa.ui.smartbox.data.PredTopFilters.*;
import static com.superit.smart.qa.ui.smartbox.data.TestValuationsUI.*;
import static com.superit.smart.qa.ui.smartbox.pages.AssetsPage.getAssetsPage;
import static com.superit.smart.qa.ui.smartbox.pages.SideBar.getSideBar;
import static com.superit.smart.qa.ui.smartbox.pages.modals.NotificationModalWrapper.MsgText.BATCH_UPD_DIRTY;
import static com.superit.smart.qa.ui.smartbox.pages.widgets.MarketValueDetailsWidget.ColId.USE_TYPE;
import static com.superit.smart.qa.ui.smartbox.service.LoginTestService.loginAndOpen;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

/**
 * Created by R0manL.
 */

@Tag("smartbox")
@Tag("ui")
@Tag("storybook")
@Tag("maindata")
@ExtendWith(PWContextExtension.class)
class MasterDataTests {
    @DisplayName("107001 Verify mandatory fields in lll widget.")
    @Tag("rl@test.com")
    @Test
    void verifyMandatoryFieldsInLinksWidgetOnContracts() {
        final String CONTRACT = PredTestData.GLOBAL_GROUP_ON_CONTRACTS_PAGE.ITEM_REAL_ESTATE_CONTRACTS.getNAME();
        final String CONTRACT_ID = PredTestData.GLOBAL_GROUP_ON_CONTRACTS_PAGE.ITEM_REAL_ESTATE_CONTRACTS.CONTRACT_2800_00300_0002.getName();

        loginAndOpen(MASTER_DATA);

        AssetsPage assetsPage = getSideBar()
                .searchAndSelectItem(CONTRACT);

        assetsPage
                .getFilterRowPanel()
                .clickSingleEntityView();

        assetsPage
                .getEntityListWidget()
                .searchAndSelectByTitle(CONTRACT_ID);
    }

    @DisplayName("10003 Verify Market Value Appraisals with Market value details widget.")
    @Tag("rl@test.com")
    @Test
    void verifyMarketValueAppraisalsWithMaintenanceValueDetailWidget() {
        final String PRED_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_MARKET_VALUE_APPRAISALS.getNAME();

        loginAndOpen(MASTER_DATA);

        getSideBar().searchAndSelectItem(PRED_ITEM_NAME);
        AssetsPage assetsPage = getAssetsPage();

        assetsPage.getHeaderRowPanel().expandSearchAndSelectTopFilterIfNotSelectedBy(TOP_FILTER_TC102926);

        assetsPage
                .getFilterRowPanel()
                .clickSingleEntityView();

        CreateEntityWizard createEntityWizard = assetsPage
                .getFilterRowPanel()
                .clickCreateEntity();

        createEntityWizard.createValuationEntity(VALUATION_TC102926);

        assetsPage
                .getEntityListWidget()
                .getEntities()
                .shouldBe(single());

        MarketValueDetailsWidget widget = new MarketValueDetailsWidget();

        MarketValueDetailsModal modal = widget.clickAdd();
        modal
                .searchAndSelectFrom(MarketValueDetailsModal.Dropdown.USE_TYPE, VALXML_OFFICE_OFFICE)
                .clickSave();

        waitOnLoadersDisappearAndCheckErrors();
        new NotificationModalWrapper().waitOnNotificationAppearsThenDisappear(BATCH_UPD_DIRTY);

        widget.clickEdit();

        widget
                .updateCell(USE_TYPE, VALXML_LEISURE_LEISURE, VALXML_OFFICE_OFFICE)
                .clickSave();

        widget.getCell(USE_TYPE).shouldHas(text(VALXML_LEISURE_LEISURE));
    }
}