package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class ContractsLinkItem {
    private String designationshort;
    private String id;
    private int lid;
    private ContractConditionContractTypeKeyValue contracttypeKeyValue;

}