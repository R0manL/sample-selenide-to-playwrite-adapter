package com.superit.smart.qa.ui.smartbox.enums;

public enum CreditRatingType {
    FI_ST_SHORT_TERM("FI_ST Short-term"),
    FI_LT_LONG_TERM("FI_LT Long-term"),

    SP_LT_LONG_TERM("SP_LT Long-term"),
    SP_ST_SHORT_TERM("SP_ST Short-term"),

    MO_LT_LONG_TERM("MO_LT Long-term"),
    MO_ST_SHORT_TERM("MO_ST Short-term"),

    DU_CI_CAPITAL_INDICATOR("DU_CI Capital Indicator"),
    DU_RI_RISK_INDICATOR("DU_RI Risk Indicator"),
    DU_IF_BC_INDICATOR_FOR_BUSINESS_CLOSURE("DU_IfBC Indicator for Business Closure"),
    DU_CC_YEAR_END_CLOSING_DATE("DU_CC Year-end closing date"),
    DU_PMFS_PORTFOLIO_MANAGENENT_FAILURE_SCORE("DU_PMFS Portfolio Management Failure Score"),
    DU_CPI_CURRENT_PAYMENT_INDEX("DU_CPI Current Payment Index"),
    DU_PO_D_PROBABILITY_OF_DEFAULT("DU_PoD Probability of Default"),
    DU_CFS_CURRENT_FAILURE_SCORE("DU_CFS Current Failure Score");

    private final String type;

    CreditRatingType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType();
    }

    public String getType() {
        return type;
    }
}