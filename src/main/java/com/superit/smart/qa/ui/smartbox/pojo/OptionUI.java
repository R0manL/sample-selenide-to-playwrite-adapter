package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.OptionHolder;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfCancellation;
import com.superit.smart.qa.utils.NumberUtils;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.addIfNotNullOrEmpty;
import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class OptionUI {
    private LocalDate date;
    private OptionHolder optionHolder;
    private int numberOfPeriods;
    @NotNull
    private TypeOfCancellation typeOfCancellation;
    private Float penalty;
    private String currencyOfPenalty;
    private String comment;
    private Integer advanceNoticeInMonths;

    public List<String> getNonEmptyValues() {
        List<String> result = new ArrayList<>();

        addIfNotNullOrEmpty(result, getTextFrom(date));
        result.add(optionHolder.toString());
        result.add(String.valueOf(numberOfPeriods));
        result.add(typeOfCancellation.toString());

        if (penalty != null) {
            result.add(NumberUtils.convertToFloatStringWithTwoDecimals(penalty));
        }

        addIfNotNullOrEmpty(result, getTextFrom(currencyOfPenalty));
        addIfNotNullOrEmpty(result, getTextFrom(comment));

        return result;
    }

    public List<String> getUniqueValues() {
        List<String> result = new ArrayList<>();

        addIfNotNullOrEmpty(result, getTextFrom(date));
        result.add(optionHolder.toString());
        result.add(typeOfCancellation.toString());

        return result;
    }
}