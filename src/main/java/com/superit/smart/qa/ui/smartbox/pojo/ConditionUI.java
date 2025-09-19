package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class ConditionUI {
    @NotNull String loan;
    @NotNull LocalDate conditionValidFrom;
    @NotNull String interestBase;
    @NotNull String amortisationType;
}
