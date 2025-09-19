package com.superit.smart.qa.ui.smartbox.pages.components.modal;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class TableCreatingModalWrapper extends ModalWrapper {
    private static final WebElm MODAL_ROOT_ELM = $("control-it-table-creating-modal");
    private static final String FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE = "//*[contains(@class, 'widget-field-title') " +
            "and normalize-space(.)='%s']/../..";

    @Override
    public WebElm getField(@NotNull String caption) {
        return modalRootElm.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }

    @Override
    public WebElm getField(@NotNull String caption, WebElm parent) {
        return parent.$(String.format(FIELD_WITH_CAPTION_XPATH_SELECTOR_TEMPLATE, caption));
    }


    public TableCreatingModalWrapper() {
        super(MODAL_ROOT_ELM);
    }
}
