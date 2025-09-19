package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.NumberUtils.convertToFloatStringWithFourDecimals;
import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class CurrencyRateFixingUI {
    @NotNull
    String fromCurrency;
    @NotNull
    LocalDate date;
    @NotNull
    String rate;
    @NotNull
    String session;
    String asset;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        result.add(asset);
        result.add(session);
        result.add(getTextFrom(date));
        result.add(convertToFloatStringWithFourDecimals(rate));

        return result;
    }
}