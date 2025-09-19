package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Builder
@Getter
public class ExpandRowUI {

    @NotNull
    private String expandRow;
    private int tableSize;
    @Singular
    @NotNull
    private List<String> displayedRows;

}