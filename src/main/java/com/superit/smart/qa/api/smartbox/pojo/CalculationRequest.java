package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by R0manL.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@ToString
public class CalculationRequest {
    private final String templateIdent;
    private final long calcRequestLevel;
    private final String sessionDetailIdent;
    private final List<Integer> entityLID;
    private final String batchWorkFlowIdent;
    private final String batchXML;
    private final long currentTaskContext;
    private final String overrideParams;
    private final boolean waitCalc;
    private final boolean optimize;
    private final Object debugPeriods;
    private final long debugLevel;
    private final boolean noTreeService;
    private final String customContext;
}