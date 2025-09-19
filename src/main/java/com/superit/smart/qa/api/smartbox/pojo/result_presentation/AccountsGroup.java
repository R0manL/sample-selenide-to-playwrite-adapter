package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountsGroup {
    private final List<Object> accounts;
    private final String type;
    private final String scale;
    private final boolean lStates;
    private final String lStatesType;
    private final List<Object> kpis;
}
