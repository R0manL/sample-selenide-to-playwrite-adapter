package com.superit.smart.qa.ui.smartbox.pages.components.link;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

@Slf4j
public class LinkWithTextWrapper {
    private final WebElm link;

    public LinkWithTextWrapper(LinkText value, WebElm parent) {
        link = parent.$("//a[normalize-space(text())='" + value.getText() + "']");
    }

    public WebElm getWebElm() {
        return link;
    }

    public void click() {
        link.click();
    }

    public boolean isActive() {
        return link.hasCssClass("active");
    }

    public enum LinkText {
        ALL("All"),
        CURRENT("Current");

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
