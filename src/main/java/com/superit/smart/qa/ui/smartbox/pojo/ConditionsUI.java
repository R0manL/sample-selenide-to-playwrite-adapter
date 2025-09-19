package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class ConditionsUI {
    // Interest Payments
    LocalDate conditionValidFrom;
    String fixedInterestRate;
    String lowerLimit;
    String upperLimit;
    String interestCalculation;
    String basePaymentDates;
    String interestIndex;
    String marginRate;
    String numberOfInterestRates;
    String endOfFixedInterestRate;
    Boolean capitalizationOfInterest;
    Boolean capitalizationOfMargin;
    String interestPayment;
    @NotNull
    String interestBase;
    String additionalCapitalizedInterest;

    // Comment
    String comment;

    // General repayment settings
    @NotNull
    String amortisationType;
    String amortisationStart;
    String numberOfAmortisationRates;

    // Linear repayment
    String amortisationAmount;

    // Annuity repayment
    String initialAmortisationRate;
    Boolean annuityExactToTheDay;
    String baseAnnuityCalculation;
    String annuity;
    boolean inclCosts;
    Boolean annCalcBaseEqualsRemainingDebt;

    // Maturity repayment
    String finalMaturityDate;

    // Variable repayment
    String repaymentIndex;
}
