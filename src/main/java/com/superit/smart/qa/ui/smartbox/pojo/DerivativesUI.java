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
public class DerivativesUI {
    @NotNull
    String designation;
    @NotNull
    LocalDate residualDebt;
    @NotNull
    String typeOfOption;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        result.add(designation);
        result.add(getTextFrom(residualDebt));
        result.add(typeOfOption);

        return result;
    }
}