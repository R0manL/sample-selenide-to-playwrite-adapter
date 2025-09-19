package com.superit.smart.qa.api.smartbox.pojo.customization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CustomizationViewResponse {
    private final int count;
    private final List<CustomizationViewProfile> customizations;
}
