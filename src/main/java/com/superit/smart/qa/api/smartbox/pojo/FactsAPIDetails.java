package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class FactsAPIDetails {
    @JsonProperty("SessionDetailLIDs")
    private final List<Integer> sessionDetailLIDs;
    @JsonProperty("PeriodFrom")
    private final int periodFrom;
    @JsonProperty("PeriodTo")
    private final int periodTo;
    @JsonProperty("PropertyLIDs")
    private final List<Object> propertyLIDs;
    @JsonProperty("PortfolioLIDs")
    private final List<Object> portfolioLIDs;
    @JsonProperty("CompanyLIDs")
    private final List<Object> companyLIDs;
    @JsonProperty("ClientId")
    private final int clientId;
    @JsonProperty("IgnoreList")
    private final List<String> ignoreList;
    @JsonProperty("RunId")
    private final String runId;
    @JsonProperty("PartIds")
    private final List<Object> partIds;
}