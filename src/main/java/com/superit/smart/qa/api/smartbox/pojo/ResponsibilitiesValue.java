package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponsibilitiesValue {
    @JsonProperty("businesspartner")
    public Integer businesspartner;
    @JsonProperty("replcement")
    public Object replcement;
    @JsonProperty("type")
    public Integer type;
    @JsonProperty("validfrom")
    public String validfrom;
    @JsonProperty("validto")
    public String validto;
    @JsonProperty("lid")
    public Integer lid;
    @JsonProperty("businesspartnerLink")
    public BusinesspartnerLink businesspartnerLink;
    @JsonProperty("replcementLink")
    public Object replcementLink;
    @JsonProperty("typeKeyValue")
    public Object typeKeyValue;
}