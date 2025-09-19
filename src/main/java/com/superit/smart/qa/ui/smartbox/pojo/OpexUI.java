package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class OpexUI {
    @NotNull String asset;
    @NotNull String contractPartner;
    @NotNull String assetId;
    @NotNull String contractId;
    @NotNull String contractType;
    @NotNull String designation;
    @NotNull String conditionId;
    String paymentInterval;
    String fixedValue;
    String noticePeriodInMonths;
}
