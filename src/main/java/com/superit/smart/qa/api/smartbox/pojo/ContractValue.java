package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ContractValue {
    private String id;
    private int lid;
    private int contracttype;
    private String designationshort;
    private ContracttypeKeyValue contracttypeKeyValue;
    private SessiondetaillidLink sessiondetaillidLink;
}
