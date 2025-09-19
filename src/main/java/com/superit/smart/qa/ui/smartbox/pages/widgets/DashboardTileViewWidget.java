package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

@Slf4j
public class DashboardTileViewWidget {
    private static final WebElm ROOT_ELM = $("control-it-dashboard-properties-info");

    public WebElm getOverviewAssetsAdditionalValueFor(OverviewAssetsAdditionalValue value) {
        log.info("Get overview asset additional value element: {0}");
        return $(".dashboard-item.overview-item").$("[data-attr-qa = '" + value + "']");
    }

    public WebElm getKpiAdditionalValueFor(KPIAdditionalValue value) {
        log.info("Get KPI additional value element: {0}");
        return $(".dashboard-item.financial-item").$("[data-attr-qa = '" + value + "']");
    }

    public WebElm getFirstTileElm() {
        log.info("Get first tile element");
        return getTileElms().first();
    }

    public WebElm getTileElms() {
        log.info("Get all tile element");
        return ROOT_ELM.$$(".dashboard-item")
                .shouldHave(Condition.visibleAtLeastOne);
    }

    public WebElm getRedirectLinkForFirstTile() {
        return getFirstTileElm().$(".icon-menu-multi-row");
    }

    public String getFirstTileTitleText() {
        return getFirstTileElm().$(".dashboard-property-title .app-text-overflow").getText();
    }

    public WebElm getFirstTileYearTitlesElms() {
        log.info("Get first card element row names");
        return getFirstTileElm().$(".property-field.head").$$(".percent");
    }

    public DashboardTileViewWidget expandOverviewAndKpiWidgetsIfCollapsed() {
        WebElm collapseExpandWidgetsButtonElm = $("//button[@data-attr-qa='widgetsExpanded-show-btn']");
        if (collapseExpandWidgetsButtonElm.hasCssClass("is-green")) {
            collapseExpandWidgetsButtonElm.click();
        } else {
            log.warn("Widgets have already collapsed.");
        }

        return this;
    }

    public enum OverviewAssetsAdditionalValue {
        PORTFOLIOS("Portfolios-additionalValue"),
        COUNTRIES("Countries-additionalValue"),
        NUMBER_OF_REAL_ASSETS("Number of real estate assets-additionalValue"),
        LETTABLE_AREA("Lettable area-additionalValue"),
        POTENTAIL_RENT("Potential rent (ann.)-additionalValue"),
        FAIR_VALUE_FX("Fair value from overall appraisal-additionalValue"),
        IRR("IRR-additionalValue");

        private final String label;

        OverviewAssetsAdditionalValue(@NotNull String label) {
            this.label = label;
        }

        @Override
        public String toString() { return label; }
    }

    public enum KPIAdditionalValue {
        CONTRACTED_RENT_ANN("Contracted rent (ann.)-additionalValue"),
        CONTRACTED_RENT_YIELD("Contracted rent yield (Spot)-additionalValue"),
        DELTA("Delta (FV-NPP)-additionalValue"),
        DELTA_FV_NPP("Delta (FV-NPP) in %-additionalValue"),
        MARKET_RENT_VACANCY_RENTAL_UNIT_ANN("Market rent | vacant rental units (ann.)-additionalValue"),
        NET_PURCHASE_PRICE("Net purchase price-additionalValue"),
        OCC_RARE_ECONOMIC("Occupancy rate | economic-additionalValue"),
        OCC_RATE_AREA("Occupancy rate | area-additionalValue"),
        OVER_UNDERRENT("Over-/Underrent | +/-  in %-additionalValue"),
        RENT_AREA("Rented area-additionalValue"),
        VACANT_AREA("Vacant area-additionalValue"),
        WAULT("WAULT | earliest possible end date-additionalValue");

        private final String label;

        KPIAdditionalValue(@NotNull String label) {
            this.label = label;
        }

        @Override
        public String toString() { return this.label; }
    }
}
