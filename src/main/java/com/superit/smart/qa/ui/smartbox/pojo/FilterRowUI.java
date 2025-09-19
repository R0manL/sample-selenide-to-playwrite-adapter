package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.TopFilterCriteria;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterLevel;
import com.superit.smart.qa.ui.smartbox.enums.TopFilterOperator;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@Getter
@Builder
public class FilterRowUI {

    @NotNull
    private TopFilterLevel level;
    @NotNull
    private TopFilterCriteria criteria;

    private String kpi;
    @NotNull
    private TopFilterOperator operator;
    @Singular
    private List<String> values;
}
