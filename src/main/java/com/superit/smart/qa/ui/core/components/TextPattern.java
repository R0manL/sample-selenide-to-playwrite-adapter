package com.superit.smart.qa.ui.core.components;

import java.util.regex.Pattern;

public class TextPattern {
    public static final Pattern STARTS_FROM_NUMBER = Pattern.compile("^\\s*\\d+");
    public static final Pattern END_WITH_PERCENT_SYMBOL = Pattern.compile(".+%$");
    public static final Pattern ANY_TEXT = Pattern.compile(".+");
    public static final Pattern STARTS_FROM_FOUR_DIGITS = Pattern.compile("^\\d{4}$");
}
