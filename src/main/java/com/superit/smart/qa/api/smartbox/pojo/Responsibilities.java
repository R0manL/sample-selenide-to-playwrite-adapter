package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Responsibilities {
    @JsonProperty("@odata.context")
    public String odataContext;
    @JsonProperty("value")
    public List<ResponsibilitiesValue> value;
}