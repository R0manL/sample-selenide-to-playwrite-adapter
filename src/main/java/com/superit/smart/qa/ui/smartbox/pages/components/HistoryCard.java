package com.superit.smart.qa.ui.smartbox.pages.components;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;

/**
 * Created on 26.07.2022
 */

@Slf4j
public class HistoryCard {

    private final WebElm historyCardElm;
    private final WebElm sectionWithChanges;

    private static final String COMMENTS_WIDGET_LOCATOR = ".comments-widget";


    public HistoryCard(WebElm historyCardElm) {
        this.historyCardElm = historyCardElm;
        this.sectionWithChanges = this.historyCardElm.$(".card-history-content-before");
    }

    public WebElm getTitle() {
        return historyCardElm.$(".card-history-widget-info");
    }

    public WebElm getComment() {
        return historyCardElm.$(COMMENTS_WIDGET_LOCATOR);
    }

    public WebElm getCommentTitle() {
        return historyCardElm.$(".card-history-title .fm-head-title");
    }

    public WebElm getCommentNewValue() {
        return sectionWithChanges.$(COMMENTS_WIDGET_LOCATOR).get(0);
    }

    public WebElm getCommentOldValue() {
        return sectionWithChanges.$(COMMENTS_WIDGET_LOCATOR).get(1);
    }

    public HistoryCard expandHistoryCardContent() {
        getComment().shouldBe(visible);

        if (!this.sectionWithChanges.isDisplayed()) {
            getComment().click();
            this.sectionWithChanges.shouldBe(visible);
        } else {
            log.warn("Comment section has already expanded. Skip.");
        }

        return this;
    }

    public void expandCommentSectionDetails() {
        getCommentTitle().click();
        getCommentNewValue().shouldBe(visible);
    }
}