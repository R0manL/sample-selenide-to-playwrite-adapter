package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class ExactTextsOneOfCondition implements Condition {

    private final String[] texts;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check '{}' with at least on from exact texts: {} is visible.", locator.toString(), Arrays.toString(texts));

        Locator result = locator.getByText(texts[0]);
        for (int i = 1; i < texts.length; i++) {
            result = result.or(locator.getByText(texts[i]));
        }

        assertThat(result).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "have at least one element with one from exact texts '" + Arrays.toString(texts) + "'";
    }
}
