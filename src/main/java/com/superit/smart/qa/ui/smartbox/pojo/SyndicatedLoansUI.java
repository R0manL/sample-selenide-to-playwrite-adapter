package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class SyndicatedLoansUI {
    @NotNull String type;
    @NotNull String creditorId;
    @NotNull String creditor;
    @NotNull String currency;
    String measureValue;
    String shareOfLoanPercent;
    String shareOfLoanAmount;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();
        result.add(type);
        result.add(creditor);
        result.add(currency);

        return result;
    }
}
