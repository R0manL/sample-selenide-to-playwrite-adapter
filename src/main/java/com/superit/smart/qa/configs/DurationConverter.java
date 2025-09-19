package com.superit.smart.qa.configs;
/* Created by R0manL. */

import org.aeonbits.owner.Converter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.time.Duration;

public class DurationConverter implements Converter<Duration> {

    @Override
    public Duration convert(Method method, @NotNull String input) {
        return Duration.ofMillis(Long.parseLong(input));
    }
}
