package com.superit.smart.qa.ui.smartbox.pages.components.section;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visibleAtLeastOne;


@Slf4j
public class PropertyListSection {
    private static final WebElm TABLE_LIST_ROOT_ELEMENT = $(".list-property");

    public WebElm getPropertyItemElms() {
        return $$(".property-item");
    }

    public PropertyListSection clickAtFirstPropertyInList() {
        TABLE_LIST_ROOT_ELEMENT.shouldBe(visible, ENV_CONFIG.webElmLoadDuration());

        getPropertyItemElms().shouldHave(visibleAtLeastOne);
        log.info("Click on the first property in the list");
        getPropertyInfoElms().first().click();

        return this;
    }

    public boolean isPropertyHighlighted(int position) {
        return getPropertyInfoElms().get(position).$(".property-title.app-text-overflow.one-lines")
                .containsCssClass("is-active");
    }

    public WebElm getCurrentStatusValueElm(@NotNull PropertyFieldMapView propertyFieldMapView) {
        return getCurrentStatusWrapperElm().$(".value[data-attr-qa='" + propertyFieldMapView.getText() + "']");
    }

    private WebElm getPropertyInfoElms() {
        return TABLE_LIST_ROOT_ELEMENT.$$("//div[@class='property-info-container']");
    }

    private WebElm getCurrentStatusWrapperElm() {
        return $(".Current-status-wrapper");
    }

    public enum PropertyFieldMapView {
        CENTER_MANAGER("marker-property-Center Manager"),
        TAX_CONSULTANT("marker-property-Company-Tax consultant"),
        COMPANY_MANAGER("marker-property-Company-Manager"),
        ASSET_MANAGER("marker-property-Asset Manager, general"),
        NUMBER_OF_RENTAL_UNITS("marker-property-Number of rental units"),
        VACANCY_RATE("marker-property-Vacancy rate | number of rental units");

        private final String text;

        PropertyFieldMapView(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return text;
        }
    }
}
