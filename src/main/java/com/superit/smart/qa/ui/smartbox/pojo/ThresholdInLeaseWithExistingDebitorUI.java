package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class ThresholdInLeaseWithExistingDebitorUI {
    @NotNull String threshold;
    @NotNull String minimumIncreaseCurrency;
    @NotNull String minimumAdjustmentFrequencyAfterIndexing;
    @NotNull String maximumIncrease;
    @NotNull String minimumIncrease;
    @NotNull String maximumIncreaseCurrency;
}