package com.superit.smart.qa.ui.smartbox.pages.menus;

import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.hidden;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visible;

public class CellCommentRightMenu {
    private static final WebElm ROOT_ELM = $(".app-aside.is-right");
    private static final WebElm TIMESTAMP_ELM = ROOT_ELM.$(".details-list-footer");

    public CellCommentRightMenu clickEdit() {
        ROOT_ELM.$(".icon-edit").click();

        return this;
    }

    public CellCommentRightMenu setCommentValue(@NotNull String value) {
        WebElm textAreaElm = ROOT_ELM.$("textarea");
        textAreaElm.setValue(value);

        return this;
    }

    public CellCommentRightMenu clickSaveComment() {
        ROOT_ELM.$(".icon-send").click();
        TIMESTAMP_ELM.shouldBe(visible);

        return this;
    }

    public CellCommentRightMenu clickDelete() {
        ROOT_ELM.$(".icon-delete").click();
        TIMESTAMP_ELM.shouldBe(hidden);
        ROOT_ELM.$(".empty-comment").shouldBe(visible);

        return this;
    }

    public CellCommentRightMenu clickCloseMenu() {
        ROOT_ELM.$(".icon-closed").click();

        return this;
    }

    public CellCommentRightMenu addCommentAndSave(@NotNull String value) {
        return clickEdit()
                .setCommentValue(value)
                .clickSaveComment();
    }
}
