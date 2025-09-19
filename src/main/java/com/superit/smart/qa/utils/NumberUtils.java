package com.superit.smart.qa.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import static com.superit.smart.qa.ui.core.components.TextConstants.PERCENT_SYMBOL;
import static com.superit.smart.qa.ui.core.components.TextPattern.STARTS_FROM_NUMBER;

/**
 * Created by R0manL.
 */

@Slf4j
public class NumberUtils {
    public static final int ONE_DIGIT_BOUND_VALUE = 99;
    public static final int TWO_DIGITS_BOUND_VALUE = 99;
    public static final int THREE_DIGITS_BOUND_VALUE = 999;

    private NumberUtils() {
        // NONE
    }

    public static float generateRandomThreeDigitsFloatValue() {
        return new SecureRandom().nextInt(THREE_DIGITS_BOUND_VALUE);
    }

    @NotNull
    public static String generateRandomThreeDigitsNumberWithFloatPart() {
        final float randomFloatValue = generateRandomThreeDigitsFloatValue();

        String result = String.format("%.2f", randomFloatValue);
        log.debug("Generate random 'MarketingRent' value: " + result);

        return String.format("%.2f", randomFloatValue);
    }

    public static int generateRandomTwoDigitsNumber() {
        return new SecureRandom().nextInt(TWO_DIGITS_BOUND_VALUE);
    }

    public static int generateRandomYearInBounds(int minYear, int maxYear) {
        return new SecureRandom().nextInt(maxYear - minYear + 1) + minYear;
    }

    public static int generateRandomOneDigitNumber() {
        return new SecureRandom().nextInt(ONE_DIGIT_BOUND_VALUE);
    }

    public static int generateRandomThreeDigitsNumber() {
        return new SecureRandom().nextInt(THREE_DIGITS_BOUND_VALUE);
    }

    @NotNull
    // Convert float (e.g 20.0000000001) to String (e.g. 20.00)
    public static String convertToFloatStringWithTwoDecimals(float value) {
        return String.format("%.2f", value);
    }

    @NotNull
    // Convert integer (e.g 20) to String (e.g. 20.00)
    public static String convertToFloatStringWithTwoDecimals(int value) {
        return convertToFloatStringWithTwoDecimals((float) value);
    }

    @NotNull
    // Convert integer (e.g 20) to String (e.g. 20.00)
    public static String convertToFloatStringWithTwoDecimals(@NotNull String value) {
        int parsedValue = Integer.parseInt(value);

        return convertToFloatStringWithTwoDecimals(parsedValue);
    }

    @NotNull
    // Convert integer (e.g 20) to String (e.g. 20.00%)
    public static String convertToFloatStringWithTwoDecimalsAndPercentSign(int value) {
        return convertToFloatStringWithTwoDecimals(value) + PERCENT_SYMBOL;
    }

    @NotNull
    // Convert float (e.g 20.0000000001) to String (e.g. 20.0000)
    public static String convertToFloatStringWithFourDecimals(float value) {
        return String.format("%.4f", value);
    }

    @NotNull
    // Convert integer (e.g 20) to String (e.g. 20.0000)
    public static String convertToFloatStringWithFourDecimals(int value) {
        return convertToFloatStringWithFourDecimals((float) value);
    }

    @NotNull
    // Convert integer (e.g 20) to String (e.g. 20.0000)
    public static String convertToFloatStringWithFourDecimals(@NotNull String value) {
        int parsedValue = Integer.parseInt(value);

        return convertToFloatStringWithTwoDecimals(parsedValue) + "00";
    }

    public static int getNextIntValueNotPresentInList(List<Integer> existValues) {
        log.info("Getting next integer value from the list.");
        Collections.sort(existValues);
        int result = existValues.get(existValues.size() - 1);
        return ++result;
    }

    public static List<Integer> convertToIntegers(List<String> values) {
        log.info("Convert list of text values to a list of integers.");
        List<Integer> result = new ArrayList<>();
        for (String s : values) result.add(Integer.valueOf(s.replace(" ", "").trim()));

        return result;
    }

    /**
     * Method convert string value into double number.
     *
     * @param value that end with measurement (e.g. 198,000.00 €, 80,000.35 qm)
     * @return double number.
     */
    public static Double convertStingWithMeasurementSignToDouble(@NotNull String value) {
        String numberString = value.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numberString);
    }

    /**
     * method takes String with numeric part, removes this non-numbers and return int
     *
     * @param text String with numeric part (e.g. "10 Results")
     * @return int (10)
     */
    public static int getNumberFromBeginningOf(@NotNull String text) {
        log.debug("Get int from the beginning of '" + text + "'.");
        Matcher m = STARTS_FROM_NUMBER.matcher(text);

        if (m.find()) {
            return Integer.parseInt(m.group());
        } else {
            throw new IllegalStateException("Can't extract positive number from the beginning of '" + text + "' text.");
        }
    }

    /**
     * Converts a string from format '1234,56' to '1,234.56'.
     *
     * @param value A string representing a number with a comma as the decimal separator.
     * @return A formatted string with thousands separator (comma) and period as the decimal separator.
     */
    public static String replaceCommaDecimalSeparatorWithDot(@NotNull String value) {
        return value.replace(',', '.');
    }

    public static String convertFloatStringToStringWithThousandSeparator(@NotNull String value) {
        // Step 1: Convert the string to a BigDecimal
        BigDecimal number;
        try {
            number = new BigDecimal(value);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }

        // Step 2: Use NumberFormat to format the number with commas as a thousand separators
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(2);  // Ensure two decimal places
        numberFormat.setMaximumFractionDigits(2);  // Limit to two decimal places

        return numberFormat.format(number);
    }
}

