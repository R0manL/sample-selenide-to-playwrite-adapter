package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class VisibleOrCondition implements Condition {
    WebElm webElm;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration timeout) {
        verify(webElm.getLocator(), timeout.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check any of: '{}' or '{}' is visible.", locator.toString(), webElm.getLocator().toString());
        assertThat(locator.or(webElm.getLocator()).nth(0)).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "any of is visible";
    }
}
