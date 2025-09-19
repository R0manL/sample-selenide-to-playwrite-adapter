package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceDataLevel {
    private Integer lid;
    private String id;
    private String name;
    private String pageUrl;
    private boolean hasRedirect;
    private Integer groupId;
}
