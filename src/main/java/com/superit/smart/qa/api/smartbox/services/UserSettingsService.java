package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.enums.UserMode;
import com.superit.smart.qa.api.smartbox.pojo.GlobalUserSettings;
import com.superit.smart.qa.api.smartbox.pojo.UserProfile;
import com.superit.smart.qa.api.enums.Language;
import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.enums.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.superit.smart.qa.api.smartbox.enums.UserModule.SETTINGS;
import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.api.smartbox.services.UserSessionService.getSessionIdByTitleForUser;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by R0manL.
 */

@Slf4j
public class UserSettingsService {
    private static final String RESOURCE_PATH = "settingsservice/api/user/settings/";
    private static final String RESET_TO_DEFAULT_PATH = RESOURCE_PATH + "ResetToDefault";
    private static final String USER_PROFILE_PATH = RESOURCE_PATH + "UserProfile";
    private static final String GLOBAL_USER_SETTINGS_PATH = RESOURCE_PATH + "GlobalUserSettings";

    private static final String PLANNING_PAGE_DEFAULT_SESSION_TITLE = "PF4700 - DEAKTIVIERT"; //
    private static final String TOPFILTER_ALL_ASSETS_ID = "0"; // ID of 'All assets' filter.
    private static final String TOPFILTER_ID_ON_PLANNING_PAGE = ENV_CONFIG.defaultPlanningTopFilterId(); // ID of 'Default' filter.
    private static final String TOPFILTER_ID_ON_REPORTING_PAGE = "52601"; // ID of 'reporting_tests' filter.
    private static final String TOPFILTER_ID_ON_DASHBOARD_PAGE = "56843"; // ID of 'reporting_tests' filter.

    @Getter private static final String PLANNING_PF4700_DEAKTIVIERT_SESSION_ID = "PF4700_3";
    @Getter private static final String PLANNING_AQA_1_SESSION_ID = "AQA_1_1";


    private UserSettingsService() {
        // None
    }

    public static void resetUserLastSessionToDefault(User user, MenuModule firstModuleToBeNavigateTo) {
        log.info("API: Reset '{}' last session (to default).", user.getUserName());

        //Set default top filter.
        String topFilterID;
        switch (firstModuleToBeNavigateTo) {
            case DASHBOARD:
                topFilterID = TOPFILTER_ID_ON_DASHBOARD_PAGE;
                break;
            default:
                topFilterID = TOPFILTER_ALL_ASSETS_ID;
        }

        log.debug("The first module for navigation is: '" + firstModuleToBeNavigateTo + "', top filter id: " + topFilterID);

        setupBBRequestSpecFor(user)
                .queryParam("topListId", topFilterID)
                .basePath(RESET_TO_DEFAULT_PATH)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK);

        log.debug("API: user's last session has been successfully reset.");
    }

    public static UserProfile getUserProfileFor(@NotNull User user) {
        log.info("API: Get profile settings for user: '{}'.", user.getEmail());
        return setupBBRequestSpecFor(user)
                .basePath(USER_PROFILE_PATH)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(UserProfile.class);
    }

    public static void setAmountOfLinksInPopup(int newValue, User user) {
        log.info("API: Set amount of links in popup to: '{}' for User: '{}'.", newValue, user.getEmail());
        UserProfile newUserProfile = getUserProfileFor(user);
        newUserProfile.setAmountOfLinksInPopup(newValue);

        setUserProfile(newUserProfile, user);
    }

    public static void setDefaultPlanningSessionInGlobalUserSettingsFor(@NotNull User user) {
        String defaultSessionId = String.valueOf(getSessionIdByTitleForUser(PLANNING_PAGE_DEFAULT_SESSION_TITLE, user));
        GlobalUserSettings globalUserSettings = getGlobalUserSettingsForUser(user);

        if (!defaultSessionId.equals(globalUserSettings.getLastSessionDetailUsed())) {
            log.info("Session updating. Current planning session is not default for user: '{}'.", user.getUserName());
            globalUserSettings.setLastSessionDetailUsed(defaultSessionId);
            log.info("Default session id is " + defaultSessionId);
            setGlobalUserSettingsForUser(globalUserSettings, user);
        } else {
            log.info("Skip session updating. Default planning session has already selected: " + defaultSessionId);
            log.info("Global user settings for user " + user + ": " + globalUserSettings);
        }
    }

    public static void setDefaultDashboardSessionInGlobalUserSettingsFor(@NotNull User user) {
        String defaultSessionId = "#MD";
        GlobalUserSettings globalUserSettings = getGlobalUserSettingsForUser(user);
            log.info("Session updating. Current planning session is not default for user: '{}'.", user.getUserName());
            globalUserSettings.setLastSessionDetailUsed(defaultSessionId);
            globalUserSettings.setPagesCustomization(null);
            globalUserSettings.setLastPageVisited("/dashboard");
            log.info("Default session id is " + defaultSessionId);
            setGlobalUserSettingsForUser(globalUserSettings, user);
    }

    public static void setAdditionalMDSessionInGlobalUserSettingsFor(@NotNull String sessionID, @NotNull User user) {
        String mdSessionID = "ACT_MD_1";

        GlobalUserSettings globalUserSettings = getGlobalUserSettingsForUser(user);
        log.info("Session updating. Current planning session is not default for user: '{}'.", user.getUserName());
        globalUserSettings.setCurrentSelectedSessions(Arrays.asList(sessionID,mdSessionID));
        globalUserSettings.setLastTopFilterUsed(TOPFILTER_ID_ON_PLANNING_PAGE);
        log.info("Additional session id is " + sessionID);
        setGlobalUserSettingsForUser(globalUserSettings, user);
    }

    public static void setUserProfile(UserProfile profile, User user) {
        log.info("API: Setting profile for user: '{}'.", user.getUserName());

        setupBBRequestSpecFor(user)
                .basePath(USER_PROFILE_PATH)
                .body(profile)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void setLanguageOnUI(Language language, User user) {
        String newLangValue = language.toString();
        log.info("API: Set UI language: {}, for user: {}", newLangValue, user);
        UserProfile newUserProfile = getUserProfileFor(user);
        newUserProfile.setUserLanguage(newLangValue);

        setupBBRequestSpecFor(user)
                .basePath(USER_PROFILE_PATH)
                .body(newUserProfile)
                .header("smartit-usermodule", SETTINGS)
                .header("userlanguage", newLangValue)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void setClientIdForUser(int clientId, User user) {
        log.info("Set ClientID='{}' for user: '{}'.", clientId, user.getEmail());
        UserProfile newUserProfile = getUserProfileFor(user);
        newUserProfile.setDefaultUserClientId(clientId);
        newUserProfile.setCurrentUserClient(clientId);

        setupBBRequestSpecFor(user)
                .basePath(USER_PROFILE_PATH)
                .body(newUserProfile)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static GlobalUserSettings getGlobalUserSettingsForUser(@NotNull User user) {
        log.info("API: Get Global User Settings.");
        return setupBBRequestSpecFor(user)
                .basePath(GLOBAL_USER_SETTINGS_PATH)
                .header("smartit-usermode", UserMode.USER.getMode())
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(GlobalUserSettings.class);
    }

    public static String setGlobalUserSettingsForUser(@NotNull GlobalUserSettings globalUserSettings, @NotNull User user) {
        log.info("API: Set Global User Settings " + globalUserSettings + " for user '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(GLOBAL_USER_SETTINGS_PATH)
                .header("smartit-usermode", UserMode.USER.getMode())
                .body(globalUserSettings)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .asString();
    }
}