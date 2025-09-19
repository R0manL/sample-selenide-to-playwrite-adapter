package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyItem {
    @JsonProperty(value = "designation_en")
    private String designationEn;
    private String id;
    private int lid;
}