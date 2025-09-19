package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class CheckInLeaseWithExistingDebitorUI {
    @NotNull LocalDate dateOfTheFirstIndexReference;
             LocalDate dateOfLastIndexing;
    @NotNull LocalDate endOfIndexAgreement;
    @NotNull String reviewFrequencyInMonth;
    @NotNull String fixedMonthOfAdjustment;
    @NotNull String adaptionOffsetInMonths;
    @NotNull String numberOfRemainingAdjustments;
    @NotNull String transferOfIndexIncrease;
}