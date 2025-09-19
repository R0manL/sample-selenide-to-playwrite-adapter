package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.ui.smartbox.pages.panels.FilterRowPanel;
import com.superit.smart.qa.ui.smartbox.pages.panels.HeaderRowPanel;
import com.superit.smart.qa.ui.smartbox.pages.widgets.*;


public class AssetsPage {


    public AssetsPage() {
        // None
    }

    public static AssetsPage getAssetsPage() {
        return new AssetsPage();
    }

    public HeaderRowPanel getHeaderRowPanel() {
        return new HeaderRowPanel();
    }

    public FilterRowPanel getFilterRowPanel() {
        return new FilterRowPanel();
    }

    public EntityListWidget getEntityListWidget() {
        return new EntityListWidget();
    }
}