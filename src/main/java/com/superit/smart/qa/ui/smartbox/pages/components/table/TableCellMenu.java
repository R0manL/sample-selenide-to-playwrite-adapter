package com.superit.smart.qa.ui.smartbox.pages.components.table;

import com.superit.smart.qa.ui.smartbox.pages.menus.CellCommentRightMenu;
import com.superit.smart.qa.ui.smartbox.pages.menus.CellHierarchyRightMenu;
import com.superit.smart.qa.ui.smartbox.pages.menus.CellHistoryRightMenu;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.utils.WaitUtils.waitOnRoundLoaderMightAppearThenDisappear;

@Slf4j
public class TableCellMenu {
    private static final String MENU_ITEM_LOCATOR_TEMPLATE = "//*[@role='menu']//li[normalize-space(text())='%s']";

    public CellCommentRightMenu selectComment() {
        selectMenuItem(MenuItem.COMMENT);
        return new CellCommentRightMenu();
    }

    public CellHistoryRightMenu selectHistory() {
        selectMenuItem(MenuItem.HISTORY);
        waitOnRoundLoaderMightAppearThenDisappear();

        return new CellHistoryRightMenu();
    }

    public CellHierarchyRightMenu selectHierarchy() {
        selectMenuItem(MenuItem.HIERARCHY);
        waitOnRoundLoaderMightAppearThenDisappear();

        return new CellHierarchyRightMenu();
    }

    private void selectMenuItem(MenuItem menuItem) {
        log.info("Select: '{}'.", menuItem.toString());
        $(String.format(MENU_ITEM_LOCATOR_TEMPLATE, menuItem)).click();
    }


    public enum MenuItem {
        HISTORY("History"),
        COMMENT("Comment"),
        HIERARCHY("Hierarchy");

        private final String text;

        MenuItem(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}