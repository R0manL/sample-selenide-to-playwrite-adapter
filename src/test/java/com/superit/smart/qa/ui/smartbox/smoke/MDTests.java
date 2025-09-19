package com.superit.smart.qa.ui.smartbox.smoke;

import com.superit.smart.qa.core.PWContextExtension;
import com.superit.smart.qa.ui.smartbox.data.PredTestData;
import com.superit.smart.qa.ui.smartbox.pages.AssetsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.superit.smart.qa.core.smartbox.enums.MenuModule.MASTER_DATA;
import static com.superit.smart.qa.ui.smartbox.pages.SideBar.getSideBar;
import static com.superit.smart.qa.ui.smartbox.service.LoginTestService.loginAndOpen;

/**
 * Created by R0manL.
 */
@Tag("smartbox")
@Tag("ui")
@Tag("smoke")
@Tag("md")
@ExtendWith(PWContextExtension.class)
class MDTests {
    @DisplayName("Verify single entity view, left filter, navigation to main page.")
    @Tag("rl@test")
    @Test
    void verifyMasterDataSingleEntityView() {
        final String PRED_ITEM_NAME = PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.getNAME();

        loginAndOpen(MASTER_DATA);

        AssetsPage page = getSideBar().searchAndSelectItem(PRED_ITEM_NAME);
        page.getFilterRowPanel().clickSingleEntityView();
        page.getFilterRowPanel().clickFilter().clickSeeResults();
    }
}
