package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Builder
@Data
public class LoanWithExistingCreditorUI {
    @NotNull String asset;
    @NotNull String smartLoan;
    String loanId;
    String typeOfLoan;
    @NotNull LocalDate conditionValidFrom;
    String fixedInterestRate;
    String marginRate;
    int lineOfCredit;
    int drawdownRemainingDebt;
}