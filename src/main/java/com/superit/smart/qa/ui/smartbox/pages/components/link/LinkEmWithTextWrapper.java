package com.superit.smart.qa.ui.smartbox.pages.components.link;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

@Slf4j
public class LinkEmWithTextWrapper {
    private final WebElm link;

    public LinkEmWithTextWrapper(LinkText btnText, WebElm parent) {
        link = parent.$("em").getByExactText(btnText.getText());
    }

    public WebElm getWebElm() {
        return link;
    }

    public void click() {
        getWebElm().click();
    }

    public enum LinkText {
        CLEAR("Clear");

        private final String text;

        LinkText(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }
    }
}
