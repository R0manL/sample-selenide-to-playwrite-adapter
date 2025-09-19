package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;

/**
 * Created by R0manL.
 */

@Slf4j
public class CustomizationStructureDetailsMenu {

    private static final WebElm MENU_ROOT_ELM = $("[role='menu'].drill-level-hierarchy");


    public WebElm getTreeNodes() {
        return MENU_ROOT_ELM.$$("mat-tree-node").filterByNotEmptyText();
    }

    public WebElm getTreeNode(Node value) {
        return getTreeNodes()
                .filterByText(value.getText())
                .shouldBe(visible);
    }

    public WebElm getTreeNode(Node value, int level) {
        return MENU_ROOT_ELM.$$("mat-tree-node[aria-level='" + level + "']").filterByText(value.getText());
    }

    public void selectTreeNode(Node value) {
        getTreeNode(value).click();
    }

    public void selectTreeNode(Node parent, Node child) {
        String parentLevel = getTreeNode(parent).getAttribute("aria-level");
        int parentLevelValue = Integer.parseInt(parentLevel);
        int childLevelValue = ++parentLevelValue;

        getTreeNode(child, childLevelValue).click();
    }

    @Getter
    public enum Node {
        RENTAL_UNITS("RENTAL UNITS"),
        LEASE_CONTRACTS("LEASE CONTRACTS"),
        ASSETS("Assets"),
        COMPANIES("Companies"),
        INTERNAL_DEBITOR("Internal Debitor");

        private final String text;

        Node(@NotNull String text) {
            this.text = text;
        }
    }
}