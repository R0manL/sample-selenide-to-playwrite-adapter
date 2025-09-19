package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OdataContextValueLease {
    @JsonProperty(value = "@odata.context")
    String odataContext;
    List<LeaseValue> value;
}
