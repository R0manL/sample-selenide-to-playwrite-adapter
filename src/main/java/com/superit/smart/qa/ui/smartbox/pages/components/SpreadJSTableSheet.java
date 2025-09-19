package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

/**
 * Created on 19.05.2022
 */

public final class SpreadJSTableSheet {

    private static final WebElm SPREAD_JS_SHEET_ROOT_ELM = $("gc-spread-sheets");

    public static SpreadJSTableSheet getSpreadJSTableSheet() {
        return new SpreadJSTableSheet();
    }

    public WebElm getRootElm() {
        return SPREAD_JS_SHEET_ROOT_ELM;
    }

    public WebElm getWorksheetCanvas() {
        return SPREAD_JS_SHEET_ROOT_ELM.$("//canvas[@gcuielement='gcWorksheetCanvas']");
    }
}
