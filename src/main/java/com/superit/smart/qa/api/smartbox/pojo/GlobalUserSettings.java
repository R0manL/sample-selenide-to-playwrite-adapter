package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GlobalUserSettings {
    private final int amountOfLinksInPopup;
    private final int defaultUserClientId;
    private final int currentUserClient;
    private final Object userCountry;
    private final Boolean isAdaptivePopupShown;
    private final Boolean isAdminMode;
    private final String userLanguage;
    private final int mode;
    private final String lastLeftFilterUsed;
    private final String tempLeftFilter;
    private String lastPageVisited;
    private String lastSessionDetailUsed;
    private String lastTopFilterUsed;
    private final String lastTopFilterWithSessionUsed;
    private String pagesCustomization;
    private final boolean showHiddenEntities;
    private final String lastMdViewUsed;
    private final String lastLeftFilterWithSessionUsed;
    private List<Object> currentSelectedSessions;
    private boolean isShowDownloadInfo;
    private boolean isShowDownloadInfoRP;
}
