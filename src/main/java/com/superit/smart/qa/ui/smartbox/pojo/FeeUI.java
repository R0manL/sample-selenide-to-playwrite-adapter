package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class FeeUI {
    @NotNull String contractType;
    @NotNull String designation;
    @NotNull String contractId;
    @NotNull String conditionId;
    @NotNull LocalDate start;
    @NotNull String reference;
    @NotNull String multiplierOnReference;
    @NotNull String paymentInterval;
}
