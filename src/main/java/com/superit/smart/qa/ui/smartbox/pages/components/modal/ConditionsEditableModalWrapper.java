package com.superit.smart.qa.ui.smartbox.pages.components.modal;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

public class ConditionsEditableModalWrapper extends ModalWrapper {
    private static final WebElm MODAL_ROOT_ELM = $("control-it-conditions-editable-modal");

    public ConditionsEditableModalWrapper() {
        super(MODAL_ROOT_ELM);
    }

    public WebElm getValidationMsg() {
        return MODAL_ROOT_ELM.$(".text-validation");
    }

    public void selectTab(@NotNull String text) {
        MODAL_ROOT_ELM.$(".tab-links").$("//a[normalize-space(text())='" + text + "']").click();
    }
}
