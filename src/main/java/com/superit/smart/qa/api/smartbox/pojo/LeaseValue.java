package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LeaseValue {
    private String id;
    private int lid;
    private int tenant;
    private TenantLink tenantLink;

    @Data
    public class TenantLink {
        private String designationshort;
        private String id;
        private int lid;
    }
}
