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
public class ChildWithExactTextCondition implements Condition {

    private final String text;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm, Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm, duration.toMillis());
    }

    private void verify(WebElm webElm, double timeout) {
        Locator elmsLocator = webElm.getLocator();
        log.debug("Check '{}' has item with exact text: '{}'", elmsLocator.toString(), text);

        Locator elm = elmsLocator.getByText(text, new Locator.GetByTextOptions().setExact(true));

        assertThat(elm).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "has item with exact text '" + text + "'";
    }
}
