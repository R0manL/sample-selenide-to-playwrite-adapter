package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserSettings {
    private final String menuId;
    private final List<DataUserBlock> settings;
}
