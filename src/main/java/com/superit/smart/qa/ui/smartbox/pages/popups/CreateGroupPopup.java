package com.superit.smart.qa.ui.smartbox.pages.popups;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.CONFIRM;


public class CreateGroupPopup {
    private static final WebElm POPUP_DIALOG_ROOT_ELM = $("control-it-cockpit-create-group");

    public CreateGroupPopup setName(@NotNull String name) {
        WebElm groupNameInput = POPUP_DIALOG_ROOT_ELM.$("input[data-attr-qa='cockpit-input-newGroupName']");

        new InputWrapper(groupNameInput)
                .sendKeys(name)
                .pressTab();

        return this;
    }

    public void clickConfirm() {
        new ButtonWithTextWrapper(CONFIRM, POPUP_DIALOG_ROOT_ELM.$(".mat-dialog-actions")).click();
        POPUP_DIALOG_ROOT_ELM.should(disappear, ENV_CONFIG.pageLoadDuration());
    }
}