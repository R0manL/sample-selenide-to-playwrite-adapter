package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class MeasureUI {
    LocalDate start;
    LocalDate end;
    @NotNull String measureType;
    String measureValue;
    @NotNull String unit;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        result.add(getTextFrom(start));
        result.add(getTextFrom(end));
        result.add(measureType);
        result.add(measureValue);
        result.add(unit);

        return result;
    }

    public List<String> getValuesWithoutMeasureValue() {
        List<String> result = new ArrayList<>();

        result.add(getTextFrom(start));
        result.add(getTextFrom(end));
        result.add(measureType);
        result.add(unit);

        return result;
    }
}
