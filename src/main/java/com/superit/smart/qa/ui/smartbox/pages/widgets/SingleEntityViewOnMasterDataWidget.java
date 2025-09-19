package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

@Slf4j
public class SingleEntityViewOnMasterDataWidget {
    private static final WebElm WIDGET_ROOT_ELM = $("control-it-widget-layout");
    private static final WebElm ASSETS_LIST_CONTAINER = WIDGET_ROOT_ELM.$(".ag-pinned-left-cols-container");


    public WebElm getSingleEntityViewRootElm() {
        return WIDGET_ROOT_ELM;
    }

    public WebElm getAssetListElms() {
        log.info("Get cell elements of row container.");

        return ASSETS_LIST_CONTAINER.$$("#multi-row");
    }
}
