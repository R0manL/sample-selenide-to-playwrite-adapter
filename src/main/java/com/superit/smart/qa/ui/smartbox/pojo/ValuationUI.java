package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class ValuationUI {
    @NotNull String asset;
    String assetId;
    @NotNull LocalDate dateOfAppraisal;
    @NotNull String realEstateAppraisalType;
    int marketValue;
}