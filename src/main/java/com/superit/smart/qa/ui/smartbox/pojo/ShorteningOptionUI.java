package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder(toBuilder=true)
public class ShorteningOptionUI {
    @NotNull LocalDate date;
    @NotNull String optionHolder;
    int numberOfPeriods;
    int advanceNoticeInMonths;
    @NotNull String status;
    String currencyOfPenalty;
    String comment;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();
        result.add(getTextFrom(date));
        result.add(optionHolder);
        result.add(String.valueOf(numberOfPeriods));
        result.add(status);

        return result;
    }
}
