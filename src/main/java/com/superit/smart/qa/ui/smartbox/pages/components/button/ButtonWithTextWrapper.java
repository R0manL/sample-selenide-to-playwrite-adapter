package com.superit.smart.qa.ui.smartbox.pages.components.button;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

@Slf4j
public class ButtonWithTextWrapper {
    private static final String IS_DISABLED_CLASS_NAME = "mat-button-disabled";
    private final WebElm buttonElm;

    public ButtonWithTextWrapper(ButtonText btnText, WebElm parent) {
        buttonElm = parent.$("button").getByExactText(btnText.getText());
    }

    public WebElm getWebElm() {
        return buttonElm;
    }

    public ButtonWithTextWrapper shouldBe(Condition condition) {
        buttonElm.shouldBe(condition);
        return this;
    }

    public boolean isActive() {
        return !buttonElm.parent().containsCssClass(IS_DISABLED_CLASS_NAME);
    }

    public void click() {
        getWebElm().click();
    }

    public enum ButtonText {

        ADD_CHECK("Add Check"),
        ADD_LEASE_BREAK_OPTIONS("Add Lease Break Options"),
        ADD_LEASE_INDEX("Add Lease index"),
        ADD_THRESHOLD("Add Threshold"),
        APPLY("Apply"),
        APPLY_AND_OPEN("Apply and open"),
        APPLY_AND_SHOW_RESULTS(" Apply and show results "),
        APPLY_SETTINGS("Apply settings"),
        BACK("Back"),
        CANCEL("Cancel"),
        CLEAR("Clear"),
        CONFIRM(" Confirm "),
        CONTINUE("Continue"),
        DELETE("Delete"),
        DELETE_WITH_SPACES(" Delete "),
        DONT_SAVE(" Don't save "),
        DOWNLOAD("Download"),
        DO_NOT_APPLY("Don’t apply"),
        DO_NOT_DUPLICATE("Don't Duplicate"),
        DUPLICATE("Duplicate"),
        MORE_DETAILS(" More details "),
        NEXT("Next"),
        NO_GO_BACK(" No, go back "),
        OK(" OK "),
        OPEN("Open"),
        RESET_ALL_FILTERS("Reset all filters"),
        RESET_ALL_DOWNLOAD_NOTIFICATIONS_POPUPS("Reset all download notifications popups"),
        RESET_TO_DEFAULT("Reset to default"),
        RESTORE(" Restore "),
        SAVE("Save"),
        SAVE_AND_APPLY("Save and apply"),
        SAVE_CHANGES("Save changes"),
        SAVE_SETTINGS("Save settings"),
        SEARCH("Search"),
        SEE_RESULTS("See results"),
        SHOW("Show"),
        SHOW_EXTENDED_DATA(" Show extended data "),
        SHOW_ONLY_SELECTED("Show only selected"),
        SHOW_RESULTS("Show results"),
        START_CALCULATION("Start calculation"),
        START_DOWNLOADING(" Start downloading "),
        USE_FILTERS_TO_CREATE_A_NEW_TOP_LIST("Use filters to create a new top list"),
        YES(" Yes "),
        YES_DELETE("Yes, delete"),
        YES_CONTINUE(" Yes, continue ");

        private final String text;

        ButtonText(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }
    }
}
