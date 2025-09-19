package com.superit.smart.qa.ui.smartbox.pojo;


import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Builder
public class TopFilterUI {

    @NotNull
    private String name;
    @NotNull
    @Singular
    private List<FilterRowUI> listFilters;
}
