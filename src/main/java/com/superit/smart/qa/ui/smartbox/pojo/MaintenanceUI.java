package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class MaintenanceUI {
    @NotNull String asset;
    @NotNull String assetId;
    @NotNull String contractId;
    @NotNull String contractPartner;
    @NotNull String designation;
    @NotNull String contractType;
    @NotNull String conditionId;
    @NotNull LocalDate start;
    @NotNull LocalDate end;
    @NotNull String noticePeriodInMonth;
}