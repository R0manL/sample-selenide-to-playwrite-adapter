package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TopFilterItem {
    @JsonProperty("lid")
    public Integer lid;
    @JsonProperty("name")
    public String name;
    @JsonProperty("isOwned")
    public Boolean isOwned;
    @JsonProperty("owner")
    public String owner;
    @JsonProperty("isFavorite")
    public Boolean isFavorite;
    @JsonProperty("order")
    public Integer order;
    @JsonProperty("lazyLoadCounter")
    public Boolean lazyLoadCounter;
    @JsonProperty("isHidden")
    public Boolean isHidden;
    @JsonProperty("isEditable")
    public Boolean isEditable;
    @JsonProperty("sessions")
    public List<Session> sessions;
}

