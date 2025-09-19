package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class NotValueContainsCondition implements Condition {

    private final String expectedValue;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check elm: '{}', not contains (partial) value: '{}'", locator.toString(), expectedValue);
        assertThat(locator).not().hasValue(Pattern.compile("\\b" + expectedValue + "\\b"), new LocatorAssertions.HasValueOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "not contains value '" + expectedValue;
    }
}
