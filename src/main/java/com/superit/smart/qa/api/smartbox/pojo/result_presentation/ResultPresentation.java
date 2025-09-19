package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultPresentation {
    @JsonProperty("name_EN")
    private final String nameEN;
    @JsonProperty("name_DE")
    private final String nameDE;
    private final String shortDescription;
    private final Settings settings;
    private final String customResource;
    private final String resource;
}