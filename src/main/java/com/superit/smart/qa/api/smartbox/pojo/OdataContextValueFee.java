package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.superit.smart.qa.api.smartbox.FeeValue;
import lombok.Data;

import java.util.List;

@Data
public class OdataContextValueFee {
    @JsonProperty(value = "@odata.context")
    String odataContext;
    List<FeeValue> value;
}