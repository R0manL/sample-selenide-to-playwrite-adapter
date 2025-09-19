package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Settings {
    private final int entityLevel;
    private final int entityLevelPreview;
    private final List<Object> levelList;
    private final List<Object> accounts;
    private final String calendarView;
    private final boolean isFinYears;
    private final String actUnit;
    private final String actCount;
    private final boolean showHistory;
    private final boolean showFuture;
    private final String planUnit;
    private final String planCount;
    private final List<Object> accountListPreview;
    private final List<Object> calcKPIs;
    private final List<Object> calcKPIsPreview;
    private final boolean allAccs;
    private final boolean showConfigMessage;
    private final ChartSettings chartSettings;
    private final ChartSettingsPreview chartSettingsPreview;
    private final CompareOptions compareOptions;
    private final int hierId;
    private final String actVer;
    private final String planVer;
    private final Object splitPeriod;
    private final String currency;
    private final String useFormat;
    private final int decimals;
    private final Object actVerVariable;
    private final Object planVerVariable;
    private final Object planCountVariable;
    private final Object actCountVariable;
    private final Object splitPeriodVariable;
}
