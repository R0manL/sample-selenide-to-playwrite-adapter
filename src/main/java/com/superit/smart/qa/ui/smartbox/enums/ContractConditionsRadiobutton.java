package com.superit.smart.qa.ui.smartbox.enums;

import org.jetbrains.annotations.NotNull;


public enum ContractConditionsRadiobutton {
        INDEXATION_BY_CONTRACT("Indexation by contract:"),
        INDEXATION_FIXED_VALUE("Indexation fixed value:"),
        INDEXATION_MAXIMUM("Indexation maximum:"),
        INDEXATION_MINIMUM("Indexation minimum:"),
        INDEXATION_REFERENCE("Indexation reference:"),
        PAYMENTS_ONLY_IN_CONTRACT_TERM("Payments only in contract term:"),
        P_AND_L_CASHFLOW("P&L = CASHFLOW:"),
        REAL_CONTRACT("Real contract:"),
        REFUND("Refund:");

        private final String title;

        ContractConditionsRadiobutton(@NotNull String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
}