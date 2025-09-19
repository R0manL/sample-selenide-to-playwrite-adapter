package com.superit.smart.qa.api.smartbox;

import com.superit.smart.qa.api.smartbox.pojo.ContracttypeKeyValue;
import com.superit.smart.qa.api.smartbox.pojo.SessiondetaillidLink;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeeValue {
    private String id;
    private int lid;
    private int contracttype;
    private String designationshort;
    private ContracttypeKeyValue contracttypeKeyValue;
    private SessiondetaillidLink sessiondetaillidLink;
    private String sessionsbezen;
}
