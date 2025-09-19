package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TopFilterResponse {
    @JsonProperty("hasNextPage")
    public Boolean hasNextPage;
    @JsonProperty("hasPreviousPage")
    public Boolean hasPreviousPage;
    @JsonProperty("items")
    public List<TopFilterItem> topFilterItems;
    @JsonProperty("pageNumber")
    public int pageNumber;
    @JsonProperty("pageSize")
    public int pageSize;
    @JsonProperty("pages")
    public int pages;
    @JsonProperty("total")
    public int total;
}

