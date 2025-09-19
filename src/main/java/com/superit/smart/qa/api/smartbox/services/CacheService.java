package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.CleanCacheResponse;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class CacheService {

    private static final String ENDPOINT_PATH = "/cachingservice/api/caching/clear-all-cache";

    @NotNull
    public static boolean clearCache(@NotNull User user) {
        log.debug("API: Clear cache for user: '{}'.", user.getUserName());

        return setupBBRequestSpecFor(user)
                .baseUri(ENV_CONFIG.smartboxApiUrl())
                .basePath(ENDPOINT_PATH)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(CleanCacheResponse.class)
                .isSucess();
    }
}
