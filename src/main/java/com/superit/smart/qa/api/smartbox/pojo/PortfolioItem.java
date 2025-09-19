package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class PortfolioItem {
    private String designationshort;
    private String designation;
    private String id;
    private int lid;
    private String sessiondetaillid;
    private SessiondetaillidLinkPortfolio sessiondetaillidLink;
}