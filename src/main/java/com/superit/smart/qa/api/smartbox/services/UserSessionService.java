package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.Session;
import com.superit.smart.qa.core.CustomCollectors;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class UserSessionService {
    private static final String GET_SESSIONS_PATH = "/webuiservice/api/webui/View/versionfilter?";

    private UserSessionService() {
        // NONE
    }


    public static String getSessionIdByTitleForUser(@NotNull String sessionTitle, @NotNull User user) {
        log.debug("API: Get sessions id of session '{}' for '{}' user.", sessionTitle, user.getEmail());
        return getSessions(user).stream()
                .filter(session -> sessionTitle.equals(session.getTitle()))
                .collect(CustomCollectors.toSingleton()).getId();
    }

    public static Session getSessionByTitleForUser(@NotNull String sessionTitle, @NotNull User user) {
        log.debug("API: Get session '{}' for '{}' user.", sessionTitle, user.getEmail());
        return getSessions(user).stream()
                .filter(session-> sessionTitle.equals(session.getTitle()))
                .collect(CustomCollectors.toSingleton());
    }

    @NotNull
    public static List<Session> getSessions(@NotNull User user) {
        log.debug("API: Get sessions list for '{}' user.", user.getEmail());
        Session[] sessions = setupBBRequestSpecFor(user)
                .basePath(GET_SESSIONS_PATH)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(Session[].class);

        return Arrays.asList(sessions);
    }
}
