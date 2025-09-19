package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum ContractLinkField {
    ASSET("zcontractasset", "Asset:", "Asset"),
    CONTRACT_PARTNER("zcontractpayer", "Contract Partner: *", "Contract Partner"),
    PAYER_EXTERNAL("zcontractpayerext", "Payer (external):", "Payer (external)"),
    INTERNAL_PAYEE("zcontractpayee", "Internal Payee:", "Internal Payee"),
    PAYEE_EXTERNAL("zcontractpayeeext", "Payee (external):", "Payee (external)");

    private final String dbFieldName;
    private final String fieldName;
    private final String viewFieldName;

    ContractLinkField(@NotNull String dbFieldName, @NotNull String fieldName, @NotNull String viewFieldName) {
        this.dbFieldName = dbFieldName;
        this.fieldName = fieldName;
        this.viewFieldName = viewFieldName;
    }

    @Override
    public String toString() {
        return this.fieldName;
    }

}
