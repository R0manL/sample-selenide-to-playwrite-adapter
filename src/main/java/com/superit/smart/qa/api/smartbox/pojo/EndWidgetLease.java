package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EndWidgetLease {
    @JsonProperty("@odata.context")
    private final String odataContext;
    private final String definiteenddate;
    private final String endfixedduration;
    private final String nextdateofterminationtenant;
    private final String nextendofcontract;
    private final String firstleaseend;
    private final Integer terminationrule;
    private final Object timelimit;
    private final Boolean transitionintounlimitedcontract;
    private final Integer lid;
    private final TerminationruleKeyValue terminationruleKeyValue;
}