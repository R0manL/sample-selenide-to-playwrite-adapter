package com.superit.smart.qa.ui.smartbox.pages.modals;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visibleAtLeastOne;
import static com.superit.smart.qa.utils.WaitUtils.waitOnWebElmAppearsAndDisappear;
import static com.superit.smart.qa.utils.WaitUtils.waitOnWebElmMightAppearThenDisappear;

@Slf4j
public class NotificationModalWrapper {
    private static final WebElm ROOT_ELM = $(".notification-modal");
    private static final WebElm NOTIFICATION_TITLE = ROOT_ELM.$(".notification-title");
    private static final WebElm NOTIFICATION_TEXT = ROOT_ELM.$(".notification-text");

    /* It's possible that 2 notifications have appeared at the same time, return all texts */
    public WebElm getNotificationModalTitleElm() {
        return NOTIFICATION_TITLE
                .should(visibleAtLeastOne, ENV_CONFIG.notificationWaitTimeout());
    }

    /* It's possible that 2 notifications have appeared at the same time, return all texts */
    public WebElm getNotificationModalsTextElms() {
        return NOTIFICATION_TEXT
                .should(visibleAtLeastOne, ENV_CONFIG.notificationWaitTimeout());
    }

    public void waitOnNotificationMightAppearThenDisappear() {
        waitOnWebElmMightAppearThenDisappear(ROOT_ELM, ENV_CONFIG.notificationWaitTimeout());
    }

    public void waitOnNotificationAppearsThenDisappear(MsgText value) {
        waitOnWebElmAppearsAndDisappear(NOTIFICATION_TEXT.filterByText(value.getText()), ENV_CONFIG.notificationWaitTimeout(), ENV_CONFIG.webElmLoadDuration());
    }

    public enum MsgTitle {
        CHANGES_ARE_BEING_PROCESSED("Changes are being processed"),
        EDITING_IS_IMPOSSIBLE("Editing is impossible");

        private final String title;

        MsgTitle(@NotNull String title) {
            this.title = title;
        }

        public String getText() {
            return this.title;
        }
    }

    @Getter
    public enum MsgText {
        ASSET_HAS_NO_STATE("This asset has no state and is unavailable for editing, please set the state"),
        ASSET_HAS_STATUS_CLOSED("This asset has the status “closed” and is unavailable for editing"),
        ASSET_HAS_STATUS_DIRECT_RESULT_INPUT("Editing assets with the status “Direct Result Input” is prohibited on this template"),
        ASSET_HAS_STATUS_REMOVED("This asset has the status “removed” and is unavailable for editing"),
        BATCH_CALC("BATCH CALC"),
        BATCH_UPD_DIRTY("BATCH UPD DIRTY"),
        END_BATCH_CALC("END BATCH CALC"),
        START_BATCH_CALC("START BATCH CALC"),
        YOUR_CHANGES_HAVE_BEEN_SAVED("Your changes have been saved!"),
        YOU_GET_NOTIF_WHEN_PROCESS_FINISHED("You will receive a notification when the processing is finished"),
        NOT_SAVED_CHANGES_MSG("Changes for some rows were not saved. We highlighted these rows and values that cause errors");

        private final String text;

        MsgText(@NotNull String text) {
            this.text = text;
        }
    }
}
