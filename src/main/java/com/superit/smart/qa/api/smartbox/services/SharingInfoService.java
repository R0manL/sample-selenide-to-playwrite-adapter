package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.sharing_info.SharingInfo;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class SharingInfoService {
    private static final String SHARING_INFO_ENDPOINT_PATH = "/webuiservice/api/webui/sharinginfo";
    private static final String SHARING_LEVEL_QUERY_PARAM = "sharingLevel";
    private static final String SHARING_INFO_LID_QUERY_PARAM = "sharingInfoLid";

    private SharingInfoService() {
        //NONE
    }

    public static SharingInfo getSharingInfoFor(int sharingInfoLid, SharingLevel sharingLevel, User user) {
        log.info("API: Get shared info for: '{}' with id: '{}' for user: '{}'",
                sharingLevel.name(), sharingInfoLid, user.getUserName());

        return setupBBRequestSpecFor(user)
                .basePath(SHARING_INFO_ENDPOINT_PATH)
                .queryParam(SHARING_INFO_LID_QUERY_PARAM, sharingInfoLid)
                .queryParam(SHARING_LEVEL_QUERY_PARAM, sharingLevel.getValue())
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(SharingInfo.class);
    }

    public static void updateSharingInfoFor(@NotNull SharingInfo updatedSharingInfo,
                                            SharingLevel sharingLevel,
                                            @NotNull User user) {
        log.info("API: Update shared info for: '{}' with id: '{}' for owner '{}'",
                sharingLevel.name(), updatedSharingInfo.getId(), user);

        setupBBRequestSpecFor(user)
                .basePath(SHARING_INFO_ENDPOINT_PATH)
                .queryParam(SHARING_LEVEL_QUERY_PARAM, sharingLevel.getValue())
                .body(updatedSharingInfo)
                .when()
                .put()
                .then()
                .statusCode(HTTP_NO_CONTENT);
    }

    enum SharingLevel {
        COCKPIT(0),
        CUSTOMIZATION(1),
        PROPERTY_LIST(2);

        final int value;

        SharingLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
