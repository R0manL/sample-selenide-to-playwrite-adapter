package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;

@Slf4j
public class CellHierarchyRightMenu {
    private static final WebElm ROOT_ELM = $("control-it-hierarchy-content");

    public WebElm getHierarchyItems() {
        return ROOT_ELM.$$(".details-list-item");
    }

    public WebElm getItemHeadTitleElm(@NotNull WebElm item) {
        return item.$(".details-head-title");
    }

    public WebElm getItemHeadNumberElm(@NotNull WebElm item) {
        return item.$(".details-head-number");
    }

    public WebElm getDetailsContentValueElm(@NotNull WebElm item) {
        return item.$("//div[normalize-space(text())='Planning Cluster']")
                .parent()
                .$(".details-content-value");
    }

    public WebElm getDetailsContentLabelsFor(@NotNull WebElm item) {
        return item.$$(".details-content .details-content-label");
    }

    @NotNull
    public WebElm getDetailsContentLabelFor(@NotNull WebElm item, DetailsContentLabel label) {
        return getDetailsContentLabelsFor(item).getByExactText(label.toString());
    }

    public WebElm getDetailsContentValueFor(@NotNull WebElm item, DetailsContentLabel label) {
        log.info("Get value element for label {}.", label.toString());
        return getDetailsContentLabelFor(item, label).$("//ancestor::*[contains(@class, 'details-content-item')]//*[contains(@class, 'details-content-value')]");
    }

    public WebElm getDetailsDot(@NotNull WebElm item) {
        return item.$(".details-dot");
    }

    public WebElm getDetailsListFooterItemFor(@NotNull WebElm item, DetailsListFooterItem footerItem) {
        return getDetailsListFooterFor(item).$(footerItem.getCssSelector());
    }

    public void closeMenu() {
        ROOT_ELM.$("//button[descendant::span[@class='icon-closed']]").click();
    }

    private WebElm getDetailsListFooterFor(@NotNull WebElm item) {
        return item.$(".details-list-footer");
    }

    public enum DetailsContentLabel {
        SESSION("Planning Cluster"),
        DEADLINE("Deadline"),
        LID("LID"),
        VARLAYER("VarLayer");

        private final String title;

        DetailsContentLabel(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public enum DetailsListFooterItem {
        TITLE(".details-footer-title"),
        DATA(".details-footer-data");

        private final String cssSelector;

        DetailsListFooterItem(@NotNull String cssSelector) {
            this.cssSelector = cssSelector;
        }

        public String getCssSelector() {
            return this.cssSelector;
        }
    }
}