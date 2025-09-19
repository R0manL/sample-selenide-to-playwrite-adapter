package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditContractLinksUI {
    private String asset;
    private String payer;
    private String payerExternal;
    private String payerExternalId;
    private String payee;
    private String payeeExternal;
    private String payeeExternalId;

}
