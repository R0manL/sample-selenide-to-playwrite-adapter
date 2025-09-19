package com.superit.smart.qa.utils;

import com.superit.smart.qa.ui.smartbox.enums.EmptyFieldText;
import com.superit.smart.qa.ui.smartbox.enums.FieldText;
import com.superit.smart.qa.utils.enums.DatePattern;
import com.superit.smart.qa.utils.enums.DecimalFormat;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;

/**
 * Created by R0manL.
 */

@Slf4j
public class StringUtils {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private StringUtils() {
        // NONE
    }

    @NotNull
    public static String generateUniqueTextBasedOnCurrentDate() {
        return generateUniqueTextBasedOnCurrentDate("");
    }

    public static String generateUniqueTextWithTestPrefixBasedOnCurrentDate() {
        return generateUniqueTextWithTestPrefixAndSuffixBasedOnCurrentDate("");
    }

    public static String generateUniqueTextWithTestPrefixAndSuffixBasedOnCurrentDate(@NotNull String suffix) {
        return generateUniqueTextBasedOnCurrentDate(ENV_CONFIG.testPrefix() + suffix);
    }

    public static String generateUniqueTextBasedOnCurrentDate(@NotNull String prefix) {
        log.debug("Generate unique string with prefix: '{}'", prefix);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssSSSS");

        return prefix + ft.format(dNow);
    }

    public static String generateUnique4DigitsTextWithTestPrefixAndSuffix(@NotNull String suffix) {
        return generateUnique4DigitsTextWithPrefix(ENV_CONFIG.testPrefix() + suffix);
    }

    public static String generateUnique6DigitsTextWithTestPrefixAndSuffix(@NotNull String suffix) {
        return generateUnique6DigitsTextWithPrefix(ENV_CONFIG.testPrefix() + suffix);
    }

    private static String generateUnique4DigitsTextWithPrefix(@NotNull String prefix) {
        log.debug("Generate unique string with prefix: '{}'", prefix);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("SSSS");

        return prefix + ft.format(dNow);
    }

    private static String generateUnique6DigitsTextWithPrefix(@NotNull String prefix) {
        log.debug("Generate unique string with prefix: '{}'", prefix);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ssSSSS");

        return prefix + ft.format(dNow);
    }

    @NotNull
    public static String getUsernameFromEmail(@NotNull String email) {
        log.debug("Getting username from: '{}' email.", email);
        int index = email.indexOf('@');

        return email.substring(0, index);
    }

    /**
     * Method remove leading numbers from String (e.g. "10 Results" > "Results")
     *
     * @param text String with numeric part (e.g. "10 Results")
     * @return text suffix value.
     */
    @NotNull
    public static String removeLeadingNumberFrom(@NotNull String text) {
        log.debug("Remove leading number from: '{}'.", text);
        Matcher m = Pattern.compile("^\\d+").matcher(text);
        return m.find() ? text.replaceAll("^\\d+", "").trim() : text;
    }

    @NotNull
    public static String getMatchedText(@NotNull String regex, @NotNull String originalString) {
        log.debug("Getting text that match regex: '{}', from '{}'.", regex, originalString);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originalString);
        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("No text match regex '{" + regex + "}' in text: " + originalString);
    }

    /**
     * Method remove leading word from String (e.g. "Ten Results" > "Results")
     *
     * @param text String with numeric part (e.g. "Ten Results")
     * @return text suffix value.
     */
    @NotNull
    public static String removeLeadingWordFrom(@NotNull String text) {
        log.debug("Remove leading number from: '{}'.", text);
        Matcher m = Pattern.compile("^\\w+").matcher(text);
        return m.find() ? text.replaceAll("^\\w+", "").trim() : text;
    }

    @NotNull
    public static String getStringWithFormat(@NotNull Float floatValue, DecimalFormat decimalFormat) {
        return new java.text.DecimalFormat(decimalFormat.getFormat()).format(floatValue);
    }

    @NotNull
    public static String getStringWithFormat(int value, DecimalFormat decimalFormat) {
        return new java.text.DecimalFormat(decimalFormat.getFormat()).format(value);
    }

    @NotNull
    public static String buildFileNameSuffixFromTestName(@NotNull String testName) {
        return testName
                .replaceAll(" ", "_")
                .replaceAll("[<>:\\\"/\\\\\\\\|?*.,']", "")
                .replaceAll("[^a-zA-Z0-9_]", "");
    }

    /**
     * Method returns leading word from String (e.g. "Ten Results" > "Ten")
     *
     * @param input contains 2 or more words (e.g. "Ten Results")
     * @return the first word of string.
     */
    public static String getLeadingWordOrNumber(String input) {
        Pattern pattern = Pattern.compile("\\b(\\w+|\\d+)\\b");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    /** Null-safe conversion of String to UI text. If value is null - return empty string, if not return original value **/
    @NotNull
    public static String getTextFrom(@Nullable String value) {
        return value == null ? "" : value;
    }

    @Nullable
    public static Integer getNumberFrom(@Nullable String value) {
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            return null;
        }
    }

    @NotNull
    public static List<String> addIfNotNullOrEmpty(List<String> values, @Nullable String value) {
        if (value != null && !value.isEmpty()) {
            values.add(value);
        }

        return values;
    }

    /** Null-safe conversion of Integer to UI text. If value is null - return empty string, if not return original value **/
    @NotNull
    public static String getTextFrom(@Nullable Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    /** Null-safe conversion of LocalDate to UI text. If value is null - return empty string, if not return original value **/
    @NotNull
    public static String getTextFrom(@Nullable LocalDate value) {
        return value == null ? "" : convertDateToStringInFormat(value, DatePattern.UI_DATE_FORMAT);
    }

    public static boolean isValidEmail(@NotNull String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @NotNull
    public static String convertBooleanToYesOrNo(boolean value) {
        return value ? FieldText.YES.getText() : EmptyFieldText.NO.getText();
    }
}
