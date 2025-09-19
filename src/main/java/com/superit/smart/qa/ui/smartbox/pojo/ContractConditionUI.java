package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class ContractConditionUI {
    @NotNull
    private String conditionId;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean realContract;
    @NotNull
    private String timeSpanOfValues;
    private String fixedValue;
    private String minimum;
    private String maximum;
    private String dataLevelForReference;
    private String entityForReference;
    private String reference;
    private String multiplierOnReference;
    private Boolean refund;
    private String checkByInterval;
    private Boolean pAndLCashFlow;
    private String factorOnEarnings;
    private String factorOnCashflow;
    private String indexationFrom;
    private String indexSeries;
    private String indexIncreaseValue;
    private Boolean indexationFixedValue;
    private Boolean indexationMinimum;
    private Boolean indexationMaximum;
    private Boolean indexationReference;
    private Boolean indexationByContract;
    @NotNull
    private String paymentInterval;
    private String paymentOffset;
    private String paymentMode;
    private Boolean paymentOnlyInContractTerms;
    private String depreciationPeriod;
    private String depreciationValue;
    private String fixedBaseForDepreciation;
    private String apportionmentRate;
    private String vacancyCostRate;
    private String startActual;
    private String endActual;
    private String projectState;
    private String liability;
    private String budgetGroup;
    @NotNull
    private String noticePeriodInMonth;
    private String reminderOfTerminationDate;
    private String dateOfAcceptanceOfRenewalOption;
}
