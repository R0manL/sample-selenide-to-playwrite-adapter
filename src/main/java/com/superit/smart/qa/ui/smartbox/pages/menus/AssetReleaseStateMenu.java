package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

/**
 * Created on 09.08.2022
 */

@Slf4j
public class AssetReleaseStateMenu {

    private static final WebElm MENU_ROOT_ELM = $(".cdk-overlay-pane");

    public void selectMenuItem(AssetReleaseStateMenuItem menuItem) {
        log.info("Select asset release state menu item by class value: '{}'", menuItem.getClassValue());
        MENU_ROOT_ELM.$("//li//span[contains(@class,'" + menuItem.getClassValue() + "')]").click();
    }

    public enum AssetReleaseStateMenuItem {
        OPEN("Open", "icon-state-open"),
        IN_PROGRESS("In Progress", "icon-state-in-progress"),
        DIRECT_RESULT_INPUT("Direct Result Input", "icon-state-direct-result"),
        CLOSED("Closed", "icon-state-closed");

        final String menuItemName;
        final String classValue;

        AssetReleaseStateMenuItem(String menuItemName, String classValue) {
            this.menuItemName = menuItemName;
            this.classValue = classValue;
        }

        public String getClassValue() {
            return classValue;
        }
    }
}

