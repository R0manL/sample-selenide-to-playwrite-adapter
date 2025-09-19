package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupTreeVersion {
    private final String dataFilterUrl;
    private final List<Group> groups;
    private final Boolean isSpecialTemplate;
    private final int maxColumnCount;
}
