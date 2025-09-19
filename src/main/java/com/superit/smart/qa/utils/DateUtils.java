package com.superit.smart.qa.utils;

import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class DateUtils {
    private static final LocalDate MIN_DATE = LocalDate.of(2016, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(2039, 12, 31);


    private DateUtils() {
        // None
    }

    public static String convertDateToStringInFormat(Date date, DatePattern pattern) {
        log.debug("Convert '{}' date to string in '{}' format.", date, pattern);

        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }

    public static String convertDateToStringInFormat(LocalDate date, DatePattern pattern) {
        log.debug("Convert '{}' local date to string in '{}' format.", date, pattern);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern.getPattern());

        return date.format(dtf);
    }

    public static LocalDate convertStringToLocalDateInFormat(@NotNull String date, DatePattern pattern) {
        log.debug("Convert '{}' to local date format with in '{}' format.", date, pattern);

        return LocalDate.parse(date, DateTimeFormatter
                .ofPattern(pattern.getPattern())
                .withLocale(Locale.ENGLISH));
    }

    public static LocalDateTime convertStringToLocalDateTimeInFormat(@NotNull String date, DatePattern pattern) {
        log.info("Convert '{}' to local date time format with in '{}' format.", date, pattern);

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern.getPattern()));
    }

    public static String convertDateToAPITimeString(@NotNull LocalDate date) {
        log.debug("Convert '{}' date to API time format.", date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.API_TIME_FORMAT.getPattern());

        return date.atStartOfDay().atOffset(ZoneOffset.UTC).format(dtf);
    }

    public static LocalDate convertDashboardPeriodToLocalDate(@NotNull String date) {
        log.debug("Convert '{}' dashboard period to date.", date);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(DatePattern.UI_YYYY_MM.getPattern())
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(Locale.ENGLISH);

        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime convertToDifferentFormat(LocalDateTime localDateTime, DatePattern newPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(newPattern.getPattern());
        String convertedDateTime = localDateTime.format(formatter);

        return LocalDateTime.parse(convertedDateTime, formatter);
    }

    /**
     * Current application behavior
     * If the current month is January - it returns true, if no - false
     */
    public static boolean isFirstMonthOfTheYear() {
        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth.equals(Month.JANUARY);
    }

    public static LocalDate generateRandomDate() {
        long daysBetween = ChronoUnit.DAYS.between(MIN_DATE, MAX_DATE);
        long randomDay = ThreadLocalRandom.current().nextLong(daysBetween - 1);
        return MIN_DATE.plusDays(randomDay);
    }

    public static void removeYearOnlyKeys(Map<String, String> map) {
        map.keySet().removeIf(key -> key.matches("\\d{4}") && !key.matches("\\d{6}"));
    }


    /**
     * Verifies that all entries with a yyyymm key:
     * - up to and including the startDate month have non-zero values
     * - after the startDate month have only "0.00" values
     *
     * @param startDate The cutoff month (inclusive) for non-zero values.
     * @param map       The map with yyyymm keys and values like "11,750.00".
     * @return true if the map meets the rule, false otherwise.
     */
    public static boolean validateValuesBasedOnStartDate(LocalDate startDate, Map<String, String> map) {
        LocalDate startMonth = startDate.withDayOfMonth(1);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();

            if (key.matches("\\d{6}")) {
                LocalDate entryDate = LocalDate.parse(key + "01",  DateTimeFormatter.ofPattern(DatePattern.UI_YYYY_MM_DD.getPattern()));
                String value = entry.getValue().replace(",", "").trim();

                if (!entryDate.isAfter(startMonth)) {
                    // Before or equal to start date → value must NOT be "0.00"
                    if ("0.00".equals(value)) {
                        return false;
                    }
                } else {
                    // After start date → value must be "0.00"
                    if (!"0.00".equals(value)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
