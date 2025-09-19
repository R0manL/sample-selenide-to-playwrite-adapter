package com.superit.smart.qa.ui.smartbox.pages.components.section;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.input.InputWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;


@Slf4j
public class GroupSection {
    private static final WebElm COCKPIT_ROOT_ELM = $("control-it-cockpit");
    private static final WebElm ITEM_ROOT_ELM = $(".cockpit-item-sortable-wrap");
    private static final WebElm GROUP_VIEW_ELM = COCKPIT_ROOT_ELM.$("[data-attr-qa='cockpit-btn-selectedGroup']");
    private static final WebElm SHOW_ALL_VIEW_ELM = COCKPIT_ROOT_ELM.$("[data-attr-qa='cockpit-btn-setDefaultGroup']");


    public WebElm getGroupItemsDescriptions() {
        return ITEM_ROOT_ELM.$$(".cockpit-item-list [data-attr-qa='cockpit-item-description']");
    }

    public GroupSection clickGroupView() {
        if (!isViewActive(GROUP_VIEW_ELM)) {
            log.info("Click 'Group View' link.");
            GROUP_VIEW_ELM.click();
        }

        return this;
    }

    public GroupSection clickShowAllView() {
        if (!isViewActive(SHOW_ALL_VIEW_ELM)) {
            log.info("Click 'Show All' view link.");
            SHOW_ALL_VIEW_ELM.click();
        }

        return this;
    }

    public GroupSection searchCockpitBy(@NotNull String name) {
        log.info("Search cockpit: '{}'.", name);
        WebElm searchInput = COCKPIT_ROOT_ELM.$("input[data-attr-qa='cockpit-input-search']");
        new InputWrapper(searchInput).sendKeys(name);

        return this;
    }

    private boolean isViewActive(WebElm viewElm) {
        return viewElm.shouldBe(visible).hasCssClass("active");
    }
}