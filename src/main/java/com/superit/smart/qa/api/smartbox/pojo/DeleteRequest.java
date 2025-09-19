package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteRequest {
    private String id;
    private String method;
    private String url;
    @JsonProperty("HEADERS")
    private PortfolioDeleteRequestHeaders headers;
}