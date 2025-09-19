package com.superit.smart.qa.ui.smartbox.pages.panels;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static com.superit.smart.qa.core.playwright.conditions.Condition.disappear;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE_SETTINGS;
import static com.superit.smart.qa.utils.FileUtils.getAbsolutePathForFileInResourceDir;
import static com.superit.smart.qa.utils.WaitUtils.waitOnRoundLoaderMightAppearThenDisappear;

@Slf4j
public abstract class SettingsPanel {
    private static final String USER_NAME_FILED_NAME = "User name";
    private static final String ROLE_FILED_NAME = "role";

    abstract WebElm getRootElm();

    public void clickSaveSettings() {
        ButtonWithTextWrapper saveBtn = new ButtonWithTextWrapper(SAVE_SETTINGS, getRootElm());
        saveBtn.click();
        saveBtn.getWebElm().should(disappear);
    }

    public void clickCloseBtn() {
        getRootElm().$(".app-aside-head span[class='icon-closed']").click();
    }

    public SettingsPanel shareWithUser(@NotNull String userName) {
        log.info("Select username to share: '{}'", userName);
        WebElm selectDropdownElm = getRootElm().$("//*[@class='field-title' " +
                "and contains(normalize-space(text()), '" + USER_NAME_FILED_NAME + "')]/../control-it-dropdown");

        new DropdownWithSearchWrapper(selectDropdownElm)
                .expand()
                .searchThenSelect(userName);

        return this;
    }

    public SettingsPanel removeSharedRole(@NotNull String name) {
        log.info("Deleting shared role: '{}'", name);
        return removeMemberFromSharedListBy(name);
    }

    public SettingsPanel shareWithRole(@NotNull String name) {
        log.info("Select role to share: '{}'", name);
        WebElm select = getRootElm().$("//*[@class='field-title' " +
                "and contains(normalize-space(text()), '" + ROLE_FILED_NAME + "')]/..//mat-form-field");

        new DropdownWrapper(select).expandAndSelectIfNotSelectedBy(name);

        return this;
    }

    public SettingsPanel removeSharedMemberBy(@NotNull String name) {
        log.info("Deleting shared member: '{}'", name);
        return removeMemberFromSharedListBy(name);
    }

    public WebElm getSharedMemberNameElms() {
        log.info("Getting shared members names.");
        return getSharedMemberElm().$$(".shared-name");
    }

    public WebElm getSharedMemberElm() {
        return getRootElm().$(".field-item.shared-list");
    }

    public WebElm getImportExcelIconElm() {
        return getRootElm().$(".import-excel-card");
    }

    public SettingsPanel clickDeleteExcelTemplate() {
        getRootElm()
                .$(".import-excel-card")
                .$(ButtonWithIconWrapper.IconClassName.DELETE.getClassName())
                .click();

        return this;
    }

    public SettingsPanel importYourExcelTemplate(@NotNull String first, @NotNull String... more) {
        Path absFilePath = getAbsolutePathForFileInResourceDir(first, more);
        getRootElm().$(".import-excel-button input").uploadFile(absFilePath);
        waitOnRoundLoaderMightAppearThenDisappear(getRootElm());

        return this;
    }

    private SettingsPanel removeMemberFromSharedListBy(@NotNull String name) {
        log.info("Removing member from shared list: '{}'.", name);
        getSharedMemberElm().$("//div[@class='shared-item ng-star-inserted']" +
                "//div[contains(normalize-space(.),'" + name + "')]" +
                "/ancestor::div[contains(@class,'shared-item')]" +
                "/button[descendant::span[@class='icon-closed']]").click();

        return this;
    }
}
