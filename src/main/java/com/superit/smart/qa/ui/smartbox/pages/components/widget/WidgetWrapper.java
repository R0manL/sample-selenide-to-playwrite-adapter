package com.superit.smart.qa.ui.smartbox.pages.components.widget;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

@Slf4j
@Getter
public class WidgetWrapper {
    protected final WebElm widgetRootElm;
    protected final WebElm emptyDataHolder;

    /**
     * Present common widget with common methods
     * @param value of ID e.g. <control-it-widget id="links">
     */
    public WidgetWrapper(WidgetID value) {
        this.widgetRootElm = $("//control-it-widget[@id='" + value.toString() + "']");
        this.emptyDataHolder = widgetRootElm.$("control-it-empty-data-holder");
    }

    public WebElm getWidgetEmptyDataElm() {
        return emptyDataHolder;
    }
}
