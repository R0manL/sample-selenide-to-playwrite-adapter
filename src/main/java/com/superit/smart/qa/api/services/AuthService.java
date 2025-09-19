package com.superit.smart.qa.api.services;

import com.superit.smart.qa.api.smartbox.pojo.UserAPISession;
import com.superit.smart.qa.core.enums.User;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by R0manL on 09.06.2021.
 */

@Slf4j
public class AuthService {
    private static final String CLIENT_ID = ENV_CONFIG.apiClientId();
    private static final String ENDPOINT_PATH = "/oauth2/v2.0/token";
    private static final String AUTH_URL = ENV_CONFIG.authUrl();
    private static final String SCOPE = ENV_CONFIG.authScope();
    private static final Map<String, UserAPISession> ACTIVE_SESSIONS = new HashMap<>();


    private AuthService() {
        // None
    }

    @NotNull
    public static String getAccessTokenFor(User user) {
        return getActiveSessionFor(user).getAccessToken();
    }

    private static UserAPISession getActiveSessionFor(User user) {
        String userName = user.getEmail();
        if (!ACTIVE_SESSIONS.containsKey(userName) || hasTokenExpiredFor(userName)) {
            UserAPISession result = requestNewAccessToken(user.getEmail(), user.getPassword());
            setActiveSessionFor(userName, result);
        }

        return ACTIVE_SESSIONS.get(userName);
    }

    private static void setActiveSessionFor(@NotNull String userName, UserAPISession userAPISession) {
        log.info("Store active session for '{}' user.", userName);
        int requestDelayInMin = 1;
        userAPISession
                .setExpiresAt(LocalDateTime.now().minusMinutes(requestDelayInMin).plusSeconds(userAPISession.getExpiresIn()));
        ACTIVE_SESSIONS.put(userName, userAPISession);
    }

    private static UserAPISession requestNewAccessToken(@NotNull String userName, @NotNull String password) {
        log.info("API: Request access token for '{}' user.", userName);

        return given()
                .contentType(ContentType.URLENC)
                .baseUri(AUTH_URL)
                .basePath(ENDPOINT_PATH)
                .formParam("client_id", CLIENT_ID)
                .formParam("username", userName)
                .formParam("password", password)
                .formParam("grant_type", "password")
                .formParam("scope", SCOPE)
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(UserAPISession.class);
    }

    private static boolean hasTokenExpiredFor(@NotNull String userName) {
        return LocalDateTime.now().isAfter(ACTIVE_SESSIONS.get(userName).getExpiresAt());
    }
}