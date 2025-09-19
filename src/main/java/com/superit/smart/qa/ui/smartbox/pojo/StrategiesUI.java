package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.ui.core.components.TextConstants.PERCENT_SYMBOL;
import static com.superit.smart.qa.utils.StringUtils.getStringWithFormat;
import static com.superit.smart.qa.utils.enums.DecimalFormat.TWO_DIGITS_AFTER_DOT;

@Data
@Builder
public class StrategiesUI {
    @NotNull String targetAllocation;
    @NotNull String targetAllocationDetail;
    int minimumInPercent;
    int maximumInPercent;
    int averageInPercent;
    int minimumValue;
    int maximumValue;
    int mean;
    int minimumAmount;
    int maximumAmount;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        result.add(targetAllocation);
        result.add(targetAllocationDetail);
        result.add(getStringWithFormat(minimumInPercent, TWO_DIGITS_AFTER_DOT) + PERCENT_SYMBOL);
        result.add(getStringWithFormat(maximumInPercent, TWO_DIGITS_AFTER_DOT) + PERCENT_SYMBOL);
        result.add(getStringWithFormat(averageInPercent, TWO_DIGITS_AFTER_DOT) + PERCENT_SYMBOL);
        result.add(getStringWithFormat(minimumValue, TWO_DIGITS_AFTER_DOT));
        result.add(getStringWithFormat(maximumValue, TWO_DIGITS_AFTER_DOT));
        result.add(getStringWithFormat(mean, TWO_DIGITS_AFTER_DOT));
        result.add(String.valueOf(minimumAmount));
        result.add(String.valueOf(maximumAmount));

        return result;
    }
}