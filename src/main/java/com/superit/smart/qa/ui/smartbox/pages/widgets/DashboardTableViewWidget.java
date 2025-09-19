package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visibleAtLeastOne;

public class DashboardTableViewWidget {

    private static final WebElm WIDGET_ROOT_ELM = $("#dashboardPropertyList");

    private DashboardTableViewWidget() {
    }

    public static DashboardTableViewWidget getDashboardTableView() {
        return new DashboardTableViewWidget();
    }

    public WebElm getDashboardLeftSection() {
        return WIDGET_ROOT_ELM.$("//div[contains(@class,'dashboard-left-section')]");
    }

    private WebElm getDashboardItemWrap() {
        return WIDGET_ROOT_ELM.$("//div[contains(@class,'dashboard-item-wrap')]")
                .shouldBe(visible);
    }

    public WebElm getDashboardItemsElms() {
        return getDashboardItemWrap().$$(".dashboard-item");
    }

    public WebElm getTileCardYearTitlesElms(@NotNull WebElm cardElm) {
        return cardElm.$(".property-field.head").$$(".percent");
    }

    public WebElm getCardElms() {
        return WIDGET_ROOT_ELM.$$("mat-card")
                .shouldHave(visibleAtLeastOne);
    }

    public String getContractedRentValueFromFirstCard() {
        String dataAttrQaValue = "card-additional-actual-30070 Annualized contracted rent (target rent resp. net cold rent) of the rental units let";

        // First card is a description section, second card is a first column with values.
        return getCardElms().get(1).$("[data-attr-qa='" + dataAttrQaValue + "']").getText();
    }

    public WebElm getVacancyRateByArea(@NotNull WebElm cardElm, @NotNull ColumnType columnType) {
        String locator = getSpanInTrendWrapLocatorWith(String.format("card-additional-percent-10040 Vacancy rate | area", columnType.getColumn()));

        return cardElm.$(locator);
    }

    public WebElm getRentedAreaFor(@NotNull WebElm cardElm, @NotNull ColumnType columnType) {
        String locator = getSpanInTrendWrapLocatorWith(String.format("card-additional-percent-10030 Rented area", columnType.getColumn()));

        return cardElm.$(locator);
    }


    public WebElm getAssetManagerGeneralValueElm(@NotNull WebElm cardElm, PropertyField propertyField) {
        return cardElm.$("//div[contains(@class,'trend-wrap')][descendant::span[@data-attr-qa='" + "card-additional-actual-" + propertyField.getText() + "']]")
                .scrollIntoView();
    }

    public WebElm getIconArrowLeftElmFor(@NotNull WebElm valueElm) {
        return valueElm.$(".icon-arrow-left");
    }

    public WebElm getTextElm(@NotNull WebElm valueElm) {
        return valueElm.$(".trend-value");
    }

    public enum ColumnType {
        PERCENT("percent"),
        ABSOLUTE("absolute");

        private final String column;

        ColumnType(@NotNull String column) {
            this.column = column;
        }

        public String getColumn() {
            return this.column;
        }

    }
    public enum PropertyField {
        ASSET_MANAGER_GENERAL("90480 Asset Manager, general"),
        PROPERTY_MANAGER_GENERAL("90520 Property manager, general"),
        ASSET_MANAGER_COMMERCIAL("90490 Asset Manager, commercial"),
        ASSET_MANAGER_TECHNICAL("90500 Asset Manager, technical"),
        ASSET_MANAGER_LETTING("90510 Asset Manager, letting"),
        PROPERTY_MANAGER_TECHNICAL("90540 Property Manager, technical"),
        CENTER_MANAGER("90550 Center Manager"),
        COMPANY_TAX_CONSULTANT("90360 Company-Tax consultant"),
        COMPANY_MANAGER("90320 Company-Manager");

        private final String text;

        PropertyField(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return text;
        }

    }
    public enum TextColor {
        GREEN("color", "rgb(62, 209, 147)"),
        RED("color", "rgb(233, 107, 75)");

        private final String cssName;
        private final String cssValue;

        TextColor(@NotNull String cssName, @NotNull String cssValue) {
            this.cssName = cssName;
            this.cssValue = cssValue;
        }

        public String getCssName() {
            return this.cssName;
        }

        public String getCssValue() {
            return this.cssValue;
        }

    }

    @NotNull
    private String getSpanInTrendWrapLocatorWith(@NotNull String dataAttrQaValue) {
        return "//div[contains(@class,'trend-wrap')][descendant::span[@data-attr-qa='" + dataAttrQaValue + "']]";
    }
}
