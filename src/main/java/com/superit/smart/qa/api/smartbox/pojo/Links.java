package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Links {
    @JsonProperty("@odata.context")
    public String odataContext;
    private List<LinkItem> items;
}