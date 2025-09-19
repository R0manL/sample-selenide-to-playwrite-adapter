package com.superit.smart.qa.ui.smartbox.pages;


import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.section.GroupSection;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

/**
 * Created by R0manL.
 */

/* Abstract page that contains cockpit section and all related functionality */

public class CockpitPage {

    protected CockpitPage() {
        //None
    }

    public static CockpitPage getCockpitPage() {
        return new CockpitPage();
    }

    public WebElm getCockpitHeaderElm() {
        return $("control-it-cockpit-header");
    }

    public WebElm getCockpitTitleElm() {
        return getCockpitHeaderElm().$("h1.text-box-title");
    }

    public GroupSection getGroupSection() {
        return new GroupSection();
    }
}
