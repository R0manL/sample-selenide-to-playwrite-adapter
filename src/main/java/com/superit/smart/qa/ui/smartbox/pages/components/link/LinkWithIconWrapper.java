package com.superit.smart.qa.ui.smartbox.pages.components.link;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

@Slf4j
public class LinkWithIconWrapper {
    private final WebElm link;

    public LinkWithIconWrapper(LinkIcon value, WebElm parent) {
        link = parent.$(value.cssLocator + " a");
    }

    public WebElm getWebElm() {
        return link;
    }

    public void click() {
        link.click();
    }

    public enum LinkIcon {

        MENU("control-it-link-menu");

        private final String cssLocator;

        LinkIcon(@NotNull String cssLocator) {
            this.cssLocator = cssLocator;
        }

        @NotNull
        public String getCssLocator() {
            return this.cssLocator;
        }
    }
}
