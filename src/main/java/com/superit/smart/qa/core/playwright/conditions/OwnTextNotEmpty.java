package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class OwnTextNotEmpty implements Condition {

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator());
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator());
    }

    private void verify(Locator locator) {
        log.debug("Check '{}' own text has not empty", locator.toString());
        assertThat(locator).not().isEmpty();
    }

    @Override
    public String toString() {
        return "own text has not empty";
    }
}
