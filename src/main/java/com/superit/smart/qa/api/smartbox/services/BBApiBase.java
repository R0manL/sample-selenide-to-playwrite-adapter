package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.enums.UserMode;
import com.superit.smart.qa.core.enums.User;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.api.services.AuthService.getAccessTokenFor;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static io.restassured.RestAssured.given;

/**
 * Created by R0manL.
 */
public class BBApiBase {
    private static final String DEFAILT_CLIENT_ID = "MA==";

    protected BBApiBase() {
        // None
    }

    @NotNull
    protected static RequestSpecification setupBBRequestSpecFor(User user) {
        return setupBBRequestSpecFor(user, DEFAILT_CLIENT_ID);
    }

    protected static RequestSpecification setupBBRequestSpecFor(User user, @NotNull String clientId) {
        return given()
                .auth().oauth2(getAccessTokenFor(user))
                .contentType(ContentType.JSON)
                .header("smartit-UserMode", UserMode.USER.getMode())
                .header("smartit-userclientid", clientId)
                .header("userlanguage", ENV_CONFIG.lang().toString())
                .baseUri(ENV_CONFIG.smartboxApiUrl());
    }
}
