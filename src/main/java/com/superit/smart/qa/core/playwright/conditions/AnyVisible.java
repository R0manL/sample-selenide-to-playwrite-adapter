package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 *  Verify if we have at leas one webElm's visible child.
 */
@Slf4j
public class AnyVisible implements Condition {

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check if '{}' return at least one visible element.", locator.toString());
        assertThat(locator.locator("visible=true").nth(0)).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "any visible";
    }

}
