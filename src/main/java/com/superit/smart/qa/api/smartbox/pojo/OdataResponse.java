package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OdataResponse {
    @JsonProperty("@odata.context")
    private final String odataContext;
    private final List<OdataResponseItem> items;
    private final String entityName;
    private final String message;
    private final String messageHtml;
    private final Object type;
    private Boolean isValidationError;
}
