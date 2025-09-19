package com.superit.smart.qa.ui.smartbox.pages;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


@Slf4j
public class UserProfilePage {
    private static final WebElm ROOT_ELM = $("control-it-profile-settings");

    public static UserProfilePage getUserProfilePage() {
        return new UserProfilePage();
    }

    public UserProfilePage selectLanguage(@NotNull Language language) {
        expandAndSelectOptionIfNotSelected(Dropdown.LANGUAGE, language.getLangName());

        return this;
    }

    public UserProfilePage selectMode(@NotNull Mode mode) {
        expandAndSelectOptionIfNotSelected(Dropdown.MODE, mode.getOption());

        return this;
    }

    public UserProfilePage selectClient(Client client) {
        expandAndSelectOptionIfNotSelected(Dropdown.CLIENT, client.getOption());

        return this;
    }

    public WebElm getResetAllDownloadNotificationsPopupsLink() {
       return new ButtonWithTextWrapper(ButtonWithTextWrapper.ButtonText.RESET_ALL_DOWNLOAD_NOTIFICATIONS_POPUPS, ROOT_ELM).getWebElm();
    }

    public void clickResetAllDownloadNotificationsPopupsIfDisplayed() {
        getResetAllDownloadNotificationsPopupsLink().clickIfVisible();
    }

    private DropdownWrapper expandAndSelectOptionIfNotSelected(Dropdown dropdown, @NotNull String option) {
        WebElm selectElm = $("[data-attr-qa='" + dropdown + "']");

        return new DropdownWrapper(selectElm).expandAndSelectIfNotSelectedBy(option);
    }

    public enum Language {
        ENGLISH("English"),
        GERMAN("German");

        private final String langName;

        Language(@NotNull String langName) {
            this.langName = langName;
        }

        public String getLangName() {
            return this.langName;
        }
    }

    public enum Mode {
        ADMIN("Admin Client"),
        USER("User");

        private final String option;

        Mode(@NotNull String option) {
            this.option = option;
        }

        public String getOption() {
            return this.option;
        }
    }

    public enum Client {
        CONTROL_IT("control.IT"),
        INTEGRATION_TESTS_DATA_SET("IntegrationTestsDataSet");

        private final String option;

        Client(@NotNull String option) {
            this.option = option;
        }

        public String getOption() {
            return this.option;
        }
    }

    private enum Dropdown {
        LANGUAGE("profile-select-currentLanguage"),
        CLIENT("profile-select-currentUserClient"),
        DEFAULT_CLIENT("profile-select-defaultUserClientId"),
        MODE("profile-select-currentUserRole");

        private final String title;

        Dropdown(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
