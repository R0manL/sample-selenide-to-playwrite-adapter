package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class ExactTextsContainInAnyOrderCondition implements Condition {

    private final List<String> texts;

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
        log.debug("Check '{}' contains exact inner texts (ignore case, any order):'{}'", elmsLocator.toString(), texts);

        texts.forEach(text -> {
            Locator elm = elmsLocator.getByText(text, new Locator.GetByTextOptions().setExact(true));

            assertThat(elm).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
        });
    }

    @Override
    public String toString() {
        return "contains exact texts (ignore case, any order)'" + texts + "'";
    }
}
