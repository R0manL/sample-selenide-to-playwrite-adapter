package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class UserProfile {
    private int amountOfLinksInPopup;
    private int currentUserClient;
    private int defaultUserClientId;
    @NotNull
    private String userLanguage;
}
