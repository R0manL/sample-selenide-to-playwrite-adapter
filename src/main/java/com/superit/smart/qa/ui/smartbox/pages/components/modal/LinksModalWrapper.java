package com.superit.smart.qa.ui.smartbox.pages.components.modal;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class LinksModalWrapper extends ModalWrapper {
    private static final WebElm MODAL_ROOT_ELM = $("control-it-links-modal");

    public LinksModalWrapper() {
        super(MODAL_ROOT_ELM);
    }


}
