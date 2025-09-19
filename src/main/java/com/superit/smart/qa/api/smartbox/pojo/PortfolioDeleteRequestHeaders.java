package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PortfolioDeleteRequestHeaders {
    @JsonProperty("CONTENT-TYPE")
    private String contentType;
}