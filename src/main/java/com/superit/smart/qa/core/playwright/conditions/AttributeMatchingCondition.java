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
public class AttributeMatchingCondition implements Condition {

    private final String attributeName;
    private final Pattern value;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        log.debug("Check elm: '{}', has attribute: '{}' with value matched by regex: '{}'", locator.toString(), attributeName, value);
        assertThat(locator).hasAttribute(attributeName, value, new LocatorAssertions.HasAttributeOptions().setTimeout(timeout));
    }

    @Override
    public String toString() {
        return "attribute '" + attributeName + "' has value matched by regex " + value;
    }
}
