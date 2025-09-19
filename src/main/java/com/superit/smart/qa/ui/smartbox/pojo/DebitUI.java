package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.NumberUtils.convertToFloatStringWithTwoDecimals;
import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class DebitUI {
    @NotNull LocalDate start;
    @NotNull LocalDate end;
    @NotNull String debitPositionType;
    float value;
    @NotNull String currency;
    @NotNull List<Boolean> months;
    String reasonForAmendment;
    String commentForDebit;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        result.add(getTextFrom(start));
        result.add(getTextFrom(end));
        result.add(debitPositionType);
        result.add(convertToFloatStringWithTwoDecimals(value));
        result.add(currency);

        return result;
    }
}