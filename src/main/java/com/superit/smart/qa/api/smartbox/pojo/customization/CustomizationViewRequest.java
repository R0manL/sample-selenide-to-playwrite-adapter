package com.superit.smart.qa.api.smartbox.pojo.customization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CustomizationViewRequest {
    private final String customResource;
    private final String filterString;
    private final String resource;
    private final int skip;
    private final int take;
}
