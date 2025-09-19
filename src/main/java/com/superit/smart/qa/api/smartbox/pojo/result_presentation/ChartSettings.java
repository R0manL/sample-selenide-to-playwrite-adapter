package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChartSettings {
    private final String actUnit;
    private final int actCount;
    private final boolean isShowActualResults;
    private final List<AccountsGroup> accountsGroup;
    private final boolean isShowHistoryFor;
    private final int histCount;
    private final String histUnit;
    private final boolean isShowFutureFor;
    private final int planCount;
    private final String planUnit;
    private final String calendarView;
}
