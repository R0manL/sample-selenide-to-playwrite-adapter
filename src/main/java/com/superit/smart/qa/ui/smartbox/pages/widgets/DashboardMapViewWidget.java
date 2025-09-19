package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.section.PropertyListSection;

import java.util.List;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


public class DashboardMapViewWidget {
    private static final WebElm PAGE_ROOT_ELM = $("control-it-dashboard-map");
    private static final WebElm ASSET_LIST_ROOT_ELM = PAGE_ROOT_ELM.$("control-it-map-properties-search");
    private static final WebElm MAP_ROOT_ELEMENT = $("//div[@class='gmap']");


    private DashboardMapViewWidget() {
    }

    public static DashboardMapViewWidget getDashboardMapView() {
        return new DashboardMapViewWidget();
    }

    public PropertyListSection getTableListProperty() {
        return new PropertyListSection();
    }

    public WebElm getInformationDialogAboutProperty() {
        return MAP_ROOT_ELEMENT.$("//div[@role='dialog']");
    }

    public WebElm getMapViewRootElement() {
        return MAP_ROOT_ELEMENT;
    }

    public List<String> getPropertyTitleTexts() {
        return ASSET_LIST_ROOT_ELM
                .$$(".property-title")
                .texts();
    }
}
