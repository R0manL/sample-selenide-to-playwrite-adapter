package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.OptionHolder;
import com.superit.smart.qa.utils.NumberUtils;
import com.superit.smart.qa.utils.enums.DatePattern;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;

@Data
@Builder
public class TerminationRightsUI {
    LocalDate date;
    @NotNull OptionHolder optionHolder;
    Integer advanceNoticeInMonths;
    String comment;
    Float penalty;
    String currencyOfPenalty;
    @NotNull String typeOfCancellation;

    public List<String> getNonEmptyValues() {
        List<String> result = new ArrayList<>();

        String date = convertDateToStringInFormat(getDate(), DatePattern.UI_DATE_FORMAT);
        result.add(date);

        result.add(optionHolder.toString());

        if (advanceNoticeInMonths != null) { result.add(String.valueOf(advanceNoticeInMonths)); }
        if (comment != null && !comment.isBlank()) { result.add(comment); }
        if (penalty != null) { result.add(NumberUtils.convertToFloatStringWithTwoDecimals(penalty)); }
        if (currencyOfPenalty != null && !currencyOfPenalty.isBlank()) { result.add(getCurrencyOfPenalty()); }

        result.add(getTypeOfCancellation());

        return result;
    }

    public List<String> getUniqueValues() {
        List<String> result = new ArrayList<>();

        String date = convertDateToStringInFormat(getDate(), DatePattern.UI_DATE_FORMAT);
        result.add(date);

        result.add(optionHolder.toString());
        result.add(getTypeOfCancellation());

        return result;
    }
}