package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum PredCustomizationViews {

    //Asset
    TC55319("TC55319"),
    TC57038_ASSET("TC57038_asset"),
    TC52844("TC52844"),
    TC_23304_MAP_VIEW_1("TC_23304_Map_view_1"),
    TC_23304_MAP_VIEW_2("TC_23304_Map_view_2"),
    TC_23304_MAP_VIEW_3("TC_23304_Map_view_3"),
    bIT_ASSET_HGB("bIT | Asset | HGB | 01"),
    bIT_ASSET_KAGB("bIT | Asset | KAGB | 01"),
    TC79026("TC79026_auto"),
    TC79026_PLANNING("TC79026_auto_planning"),
    TC79890_AUTO("TC79890_auto"),
    TC84953_AUTO("TC84953_auto"),
    TC94480("TC94480"),
    TC105821("TC105821"),
    TC29307("TC29307"),
    TC103875_BGR("TC103875_BGR"),
    TC103875_BULK("TC103875_BULK"),
    TC106624("TC106624"),
    TC106370("TC106370"),
    TC107819("TC107819"),

    // Unit
    bIT_UNIT_HGB("bIT | Unit | HGB | 01"),
    TC67448("TC67448"),

    // Loan
    LOAN_CONDITION("Loan condition"),

    //Timeline Data
    TC78641("TC78641"),
    TC96979A("TC96979A"),
    TC96979("TC96979"),

    //Lease
    TC22543("TC 22543 customization with Earliest possible end date"),
    bIT_LEASE_HGB_CUSTOMIZATION("bIT | Lease | HGB | 01"),
    bIT_LEASE_KAGB_CUSTOMIZATION("bIT | Lease | KAGB | 01"),

    //Structure
    TC57038("TC57038"),

    // Fund
    bIT_FUND_CF_01("bIT | Fund | CF | 01"),
    bIT_FUND_HGB_01("bIT | Fund | HGB | 01"),

    //Planning
    bIT_ASSET_USETYPE_HGB("bIT | Asset UseType | HGB | 01"),
    TEST_REDIRECTION("test_redirection"),
    AQA_TEST_LOANS("AQA_test_loans"),
    AQA_TEST_LOANS_PARA("AQA_test_loans_para"),
    TC108267("TC108267"),

    // Absolute plan value
    PLANNING_ASSETS_PAGE_DEFAULT_CUSTOMIZATION("Automation Assets Customization"),
    PLANNING_LEASES_PAGE_DEFAULT_CUSTOMIZATION("Automation Leases Customization"),
    API_MASTER("API_MASTER"),
    TC76891_AUTO("TC76891_auto"),

    //Contracts
    TC85293_AUTO("TC85293_auto"),

    //Sales Planning
    TC103971("TC103971"),

    //Portfolio
    TC108298("TC108298"),

    //Company
    TC108370("TC108370");

    private final String name;

    PredCustomizationViews(@NotNull String name) {
        this.name = name;
    }
}
