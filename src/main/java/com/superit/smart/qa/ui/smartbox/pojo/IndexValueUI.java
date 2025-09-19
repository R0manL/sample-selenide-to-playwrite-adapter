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
public class IndexValueUI {
    @NotNull
    LocalDate day;
    @NotNull
    String value;
    @NotNull
    String session;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();
        result.add(session);
        result.add(getTextFrom(day));
        result.add(value + ".0000");

        return result;
    }
}