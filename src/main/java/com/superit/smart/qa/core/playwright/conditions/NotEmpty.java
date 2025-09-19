package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class NotEmpty implements Condition {

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check '{}' is not empty.", locator.toString());
        assertThat(locator).not().isEmpty(new LocatorAssertions.IsEmptyOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "not empty";
    }
}
