package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusinesspartnerLink {
    @JsonProperty("designationshort")
    public String designationshort;
    @JsonProperty("id")
    public String id;
    @JsonProperty("lid")
    public Integer lid;
}