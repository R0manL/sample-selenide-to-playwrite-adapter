package com.superit.smart.qa.core.playwright.conditions;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.utils.enums.DatePattern;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDate;

import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@AllArgsConstructor
public class DateValueCondition implements Condition {

    private final LocalDate expectedDate;

    @Override
    public void verify(WebElm webElm) {
        verify(webElm.getLocator(), Configuration.DEFAULT_TIMEOUT);
    }

    @Override
    public void verify(WebElm webElm, Duration duration) {
        verify(webElm.getLocator(), duration.toMillis());
    }

    private void verify(Locator locator, double timeout) {
        String expDate = convertDateToStringInFormat(expectedDate, DatePattern.UI_DATE_FORMAT);
        log.debug("Check '{}' has date value: '{}'", locator.toString(), expDate);
        assertThat(locator).hasValue(expDate, new LocatorAssertions.HasValueOptions().setTimeout(timeout)
        );
    }

    @Override
    public String toString() {
        return "has date value '" + convertDateToStringInFormat(expectedDate, DatePattern.UI_DATE_FORMAT) + "'";
    }
}
