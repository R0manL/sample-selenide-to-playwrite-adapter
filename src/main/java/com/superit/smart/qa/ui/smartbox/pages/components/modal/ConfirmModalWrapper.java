package com.superit.smart.qa.ui.smartbox.pages.components.modal;

/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.CheckboxCaption;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.checkbox.CheckboxMatWrapper;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.sleepForWebElmRefreshDuration;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.CLOSE;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.*;

public class ConfirmModalWrapper {

    final WebElm modalRoot = $("control-it-confirm-modal");

    public WebElm getModalRoot() {
        return modalRoot;
    }

    public void clickConfirm() {
        new ButtonWithTextWrapper(CONFIRM, modalRoot).click();
    }

    public void clickSaveChanges() {
        new ButtonWithTextWrapper(SAVE_CHANGES, modalRoot).click();
    }

    public void clickYes() {
        new ButtonWithTextWrapper(YES, modalRoot).click();
    }

    public boolean isDisplayed() {
        return modalRoot.isDisplayed();
    }

    public WebElm getTitle() {
        return modalRoot.$(".modal-title");
    }

    public WebElm getMessage() {
        return modalRoot.$(".modal-message");
    }

    public void clickClose() {
        new ButtonWithIconWrapper(CLOSE, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickYesDelete() {
        new ButtonWithTextWrapper(YES_DELETE, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickYesContinue() {
        new ButtonWithTextWrapper(YES_CONTINUE, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickApply() {
        new ButtonWithTextWrapper(APPLY, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickDoNotApply() {
        new ButtonWithTextWrapper(DO_NOT_APPLY, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickDuplicate() {
        new ButtonWithTextWrapper(DUPLICATE, modalRoot).click();
        sleepForWebElmRefreshDuration(4);
    }

    public void clickCancel() {
        new ButtonWithTextWrapper(CANCEL, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickNoGoBack() {
        new ButtonWithTextWrapper(NO_GO_BACK, modalRoot).click();
        modalRoot.should(disappear);
    }

    public void clickDontSave() {
        new ButtonWithTextWrapper(DONT_SAVE, modalRoot).click();
        modalRoot.should(disappear);
    }

    public WebElm getStartDownloadingBtnElm() {
        return new ButtonWithTextWrapper(START_DOWNLOADING, modalRoot).getWebElm();
    }

    public void selectCheckBox(CheckboxCaption checkboxCaption) {
        new CheckboxMatWrapper(checkboxCaption.getCaption(), modalRoot).getCheckbox().click();
    }
}
