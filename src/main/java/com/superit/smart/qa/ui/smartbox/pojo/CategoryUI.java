package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class CategoryUI {
    @NotNull private final String assetType;
    private final String subportfolio1;
    private final String subportfolio2;
}
