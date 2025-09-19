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
public class OwnTextCaseSensitiveCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check '{}' contains own text (case sensitive): '{}'", locator.toString(), expectedText);
        assertThat(locator).containsText(expectedText, new LocatorAssertions.ContainsTextOptions()
                        .setUseInnerText(false)
                        .setIgnoreCase(false)
                        .setTimeout(timeout)
        );
    }

    @Override
    public String toString() {
        return "contains own text (case sensitive) '" + expectedText + "'";
    }
}
