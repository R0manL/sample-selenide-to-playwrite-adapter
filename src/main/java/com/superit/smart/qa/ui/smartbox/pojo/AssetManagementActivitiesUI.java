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
public class AssetManagementActivitiesUI {
    @NotNull String responsibility;
    String kindOfActivity;
    LocalDate date;
    @NotNull String activityDesignation;
    String value;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();
        result.add(responsibility);
        result.add(kindOfActivity);
        result.add(getTextFrom(date));
        result.add(activityDesignation);

        return result;
    }

    public List<String> getValuesWithoutActivityDesignation() {
        List<String> result = new ArrayList<>();
        result.add(responsibility);
        result.add(kindOfActivity);
        result.add(getTextFrom(date));

        return result;
    }

}
