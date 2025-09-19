package com.superit.smart.qa.ui.smartbox.pojo;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Data
@SuperBuilder
public class LeaseIndexInLeaseWithExistingDebitorUI {
    @NotNull String indexSeries;
    @NotNull String typeOfCheck;
    @NotNull String indexFreeType;
    @NotNull String indexValueWithAdjustment;
    @NotNull String indexFreeMonth;
    @NotNull String comment;
}