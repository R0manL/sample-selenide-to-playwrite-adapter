package com.superit.smart.qa.ui.smartbox.pages.components.section;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWithPlaceholderWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ConfirmModalWrapper;
import com.superit.smart.qa.ui.smartbox.pages.panels.CustomizeViewsFilterRowPanel;
import com.superit.smart.qa.ui.smartbox.pages.panels.CustomizeViewsSettingsPanel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.SETTINGS;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.APPLY;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE_CHANGES;
import static com.superit.smart.qa.utils.WaitUtils.waitOnLoadersDisappearAndCheckErrors;

@Slf4j
public class CustomizeViewsSection {
    private static final WebElm ROOT_ELM = $("control-it-customization-screen");
    private static final WebElm LIST_ROOT_ELM = $("control-it-customization-view-list");

    private CustomizeViewsSection() {
        //NONE
    }

    public static CustomizeViewsSection getCustomizeViewsSection() {
        return new CustomizeViewsSection();
    }

    public WebElm getRootElm() {
        return ROOT_ELM;
    }

    public CustomizeViewsSettingsPanel edit(@NotNull String name) {
        select(name);
        log.info("Click settings btn for customize view: {}.", name);
        new ButtonWithIconWrapper(ButtonWithIconWrapper.IconClassName.SETTINGS, get(name)).click();

        return new CustomizeViewsSettingsPanel();
    }

    public CustomizeViewsSection delete(@NotNull String name) {
        search(name)
                .select(name);

        new CustomizeViewsFilterRowPanel()
                .clickDeleteCustomizationView()
                .clickYesDelete();

        return this;
    }

    public WebElm getTitlesElm() {
        log.debug("Getting all customization view titles.");
        return LIST_ROOT_ELM.$$(".custmization-group-title");
    }

    public WebElm getCustomizationViewRoleElm(@NotNull String name) {
        int roleElementOrderNumber = 1;
        return get(name).$("//*[@class='cockpit-item-text']/*[@tippy]")
                .$(".app-text-overflow.one-lines")
                .get(roleElementOrderNumber);
    }

    public WebElm getCustomizationViewModeElm(@NotNull String name) {
        return get(name).$("//*[@class='cockpit-item-text']/*[@tippy]/../span[3]");
    }

    public InformationAreaSection getInformationAreaSection() {
        return new InformationAreaSection();
    }

    public WebElm getAppliedGreenMark(@NotNull String name) {
        return get(name).$(".customization-applied-status");
    }

    public boolean hasApplied(@NotNull String name) {
        return getAppliedGreenMark(name).isDisplayed();
    }

    public CustomizeViewsSection apply(@NotNull String name) {
        WebElm custView = search(name)
                .select(name)
                .get(name)
                .hover();

        new ButtonWithTextWrapper(APPLY, custView).click();

        return this;
    }

    public WebElm get(@NotNull String name) {
        return LIST_ROOT_ELM.$("//*[contains(@class,'cockpit-group-item ') and .//*[normalize-space(text())='" + name + "']]");
    }

    public WebElm getAll() {
        return LIST_ROOT_ELM.$(".cockpit-group-item");
    }

    public CustomizeViewsSection search(@NotNull String name) {
        log.info("Search customization view: '{}'.", name);
        waitOnLoadersDisappearAndCheckErrors();
        new InputWithPlaceholderWrapper(InputPlaceholder.FIND_A_VIEW, LIST_ROOT_ELM).search(name);

        return this;
    }

    public CustomizeViewsSection select(@NotNull String name) {
        log.info("Select customize view: {}.", name);
        WebElm customizeView = get(name);

        customizeView
                .$(".cockpit-group-title")
                .click();

        customizeView.shouldHas(Condition.cssClassContains("is-selected"));

        return this;
    }

    public CustomizeViewsSection searchAndSelect(@NotNull String name) {
        search(name);
        getAll().shouldBe(Condition.single(), ENV_CONFIG.webElmLoadDuration().multipliedBy(4));
        select(name);

        return this;
    }

    public void openSettings(@NotNull String name) {
        log.info("Click icon settings: {}.", name);
        new ButtonWithIconWrapper(SETTINGS, get(name)).click();
    }

    public ConfirmModalWrapper clickSaveChanges() {
        new ButtonWithTextWrapper(SAVE_CHANGES, ROOT_ELM).click();

        return new ConfirmModalWrapper();
    }


    @Getter
    public enum CustomizationViewContent {
        GLOBAL("Global"),
        READ_ONLY("Read Only");

        private final String text;

        CustomizationViewContent(String text) {
            this.text = text;
        }
    }
}
