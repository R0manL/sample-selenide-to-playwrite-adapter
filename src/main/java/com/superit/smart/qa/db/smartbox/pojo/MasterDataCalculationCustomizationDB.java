package com.superit.smart.qa.db.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MasterDataCalculationCustomizationDB {
    @JsonProperty("EntityLevel")
    private int entityLevel;
    @JsonProperty("EntityLevelPreview")
    private int entityLevelPreview;
    @JsonProperty("LevelList")
    private List<Object> levelList = null;
    @JsonProperty("Accounts")
    private List<Object> accounts = null;
    @JsonProperty("CalendarView")
    private String calendarView;
    @JsonProperty("IsFinYears")
    private Boolean isFinYears;
    @JsonProperty("actUnit")
    private String actUnit;
    @JsonProperty("actCount")
    private String actCount;
    @JsonProperty("ShowHistory")
    private Boolean showHistory;
    @JsonProperty("ShowFuture")
    private Boolean showFuture;
    @JsonProperty("planUnit")
    private String planUnit;
    @JsonProperty("planCount")
    private String planCount;
    @JsonProperty("AccountListPreview")
    private List<Object> accountListPreview = null;
    @JsonProperty("CalcKPIs")
    private List<Object> calcKPIs = null;
    @JsonProperty("CalcKPIsPreview")
    private List<Object> calcKPIsPreview = null;
    @JsonProperty("AllAccs")
    private Boolean allAccs;
    @JsonProperty("ShowConfigMessage")
    private Boolean showConfigMessage;
    @JsonProperty("ChartSettings")
    public Object chartSettings;
    @JsonProperty("ChartSettingsPreview")
    private Object chartSettingsPreview;
    @JsonProperty("CompareOptions")
    private Object compareOptions;
    @JsonProperty("HierId")
    private int hierId;
    @JsonProperty("ActVer")
    private String actVer;
    @JsonProperty("PlanVer")
    private String planVer;
    @JsonProperty("SplitPeriod")
    private int splitPeriod;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("UseFormat")
    private String useFormat;
    @JsonProperty("Decimals")
    private int decimals;
    @JsonProperty("ActCountVariable")
    private Object actCountVariable;
    @JsonProperty("PlanCountVariable")
    private Object planCountVariable;
    @JsonProperty("ActVerVariable")
    private Object actVerVariable;
    @JsonProperty("PlanVerVariable")
    private Object planVerVariable;
    @JsonProperty("SplitPeriodVariable")
    private Object splitPeriodVariable;
    @JsonProperty("IsAllEntities")
    private Object isAllEntities;
    @JsonProperty("IsInvestorView")
    private Object isInvestorView;
}