package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChartSettingsPreview {
    private final List<AccountsGroup> accountsGroup;
}
