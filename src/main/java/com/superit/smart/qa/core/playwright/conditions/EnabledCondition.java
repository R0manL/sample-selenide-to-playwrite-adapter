package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class EnabledCondition implements Condition {

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration timeout) {
        verify(webElm.getLocator(), timeout.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check '{}' has enabled.",  locator.toString());
        assertThat(locator).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "has enabled";
    }

}
