package com.superit.smart.qa.ui.smartbox.pages.components.modal;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class CurrencyCreatingModalWrapper extends ModalWrapper {
    private static final WebElm MODAL_ROOT_ELM = $("control-it-currency-creating-modal");


    public CurrencyCreatingModalWrapper() {
        super(MODAL_ROOT_ELM);
    }
}
