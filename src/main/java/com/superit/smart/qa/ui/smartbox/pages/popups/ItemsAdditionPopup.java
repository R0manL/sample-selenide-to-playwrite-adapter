package com.superit.smart.qa.ui.smartbox.pages.popups;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;


@Slf4j
public class ItemsAdditionPopup {
    private static final WebElm ROOT_ELM = $("control-it-cockpit-added-items-modal");

    public void clickSave() {
        new ButtonWithTextWrapper(ButtonWithTextWrapper.ButtonText.SAVE, ROOT_ELM).click();
    }

    public ItemsAdditionPopup selectItemBy(@NotNull String title) {
        getItemElmBy(title).click();
        return this;
    }

    public WebElm getItemElmBy(@NotNull String title) {
        log.info("Getting item by title: '{}'.", title);
        return $("//*[contains(@class, 'cockpit-item-sortable-box') and .//*[normalize-space(text())='" + title + "']]");
    }
}