package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class ContrPartner {
    private PersonLink personLink;
    private String id;
    private int lid;

    @Data
    public class PersonLink {
        private String designationshort;
    }
}
