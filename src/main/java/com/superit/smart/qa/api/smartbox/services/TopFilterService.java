package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.TopFilterEntityRequest;
import com.superit.smart.qa.api.smartbox.pojo.TopFilterEntityResponse;
import com.superit.smart.qa.api.smartbox.pojo.TopFilterResponse;
import com.superit.smart.qa.api.smartbox.pojo.sharing_info.SharingInfo;
import com.superit.smart.qa.core.CustomCollectors;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.api.smartbox.services.SharingInfoService.updateSharingInfoFor;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class TopFilterService {
    private static final String TOP_FILTER_PATH = "/webuiservice/api/PropertiesLists";
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private TopFilterService() {
        // None
    }

    public static TopFilterEntityResponse createTopFilterIfNotExist(@NotNull TopFilterEntityRequest topFilterEntity,
                                                                    @NotNull User user) {
        String newTopFilterName = topFilterEntity.getName();
        if (getTopFiltersOfUser(user).getTopFilterItems().stream().noneMatch(filter -> newTopFilterName.equals(filter.getName()))) {
            return createTopFilterByUser(topFilterEntity, user);
        } else {
            log.warn("'{}' top filter has already exist. Skip creation.", newTopFilterName);
            return getTopFilterOfUser(newTopFilterName, user);
        }
    }

    public static TopFilterEntityResponse getTopFilterOfUser(@NotNull String topFilterName, @NotNull User user) {
        log.info("Get top filter with name '{}' for user '{}'", topFilterName, user.getEmail());
        return getTopFiltersOfUser(user).getTopFilterItems().stream()
                .filter(filter -> topFilterName.equals(filter.getName()))
                .map(filter -> getTopFilterOfUser(filter.getLid(), user))
                .collect(CustomCollectors.toSingleton());
    }

    public static void updateSharedUsersFromTopFilter(List<Integer> sharedUsers,
                                                      int topFilterId,
                                                      @NotNull User userOwnerOfTopFilter) {
        log.info("Update shared Users of top filter with id '{}' for user '{}'.",
                topFilterId, userOwnerOfTopFilter.getEmail());
        final SharingInfo updateBody = SharingInfo.builder()
                .id(topFilterId)
                .sharedUsers(sharedUsers)
                .build();

        updateSharingInfoFor(updateBody, SharingInfoService.SharingLevel.PROPERTY_LIST, userOwnerOfTopFilter);
    }

    public static TopFilterResponse getTopFiltersOfUser(@NotNull User user) {
        log.debug("API: Getting all Top Filters for user: '{}'.", user.getUserName());

       int totalNumberOfFilters = getTopFiltersOfUser(user, DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER).getTotal();

        return getTopFiltersOfUser(user, totalNumberOfFilters, DEFAULT_PAGE_NUMBER);
    }

    @NotNull
    private static TopFilterResponse getTopFiltersOfUser(@NotNull User user, int pageSize, int pageNumber) {
        log.debug("API: Getting all Top Filters for user: '{}'.", user.getUserName());

        return setupBBRequestSpecFor(user)
                .basePath(TOP_FILTER_PATH + "/pages")
                .queryParam("pageSize", pageSize)
                .queryParam("pageNumber", pageNumber)
                .queryParam("isEditMode", true)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(TopFilterResponse.class);
    }

    @NotNull
    public static TopFilterEntityResponse getTopFilterOfUser(int topFilterId, @NotNull User user) {
        log.debug("API: Get '{}' Top Filter for user: '{}'.", topFilterId, user.getUserName());

        return setupBBRequestSpecFor(user)
                .basePath(TOP_FILTER_PATH + "/" + topFilterId)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(TopFilterEntityResponse.class);
    }

    public static void deleteTopFilter(int topFilterId, @NotNull User user) {
        log.debug("API: Delete Top Filter with id '{}' for user: '{}'.", topFilterId, user.getUserName());

        setupBBRequestSpecFor(user)
                .basePath(TOP_FILTER_PATH)
                .queryParam("lid", topFilterId)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_NO_CONTENT);
    }

    @NotNull
    private static TopFilterEntityResponse createTopFilterByUser(TopFilterEntityRequest topFilterPayload,
                                                                 @NotNull User user) {
        log.debug("API: Create '{}' Top Filter for user: '{}'.", topFilterPayload.getName(), user.getUserName());

        return setupBBRequestSpecFor(user)
                .basePath(TOP_FILTER_PATH)
                .body(topFilterPayload)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(TopFilterEntityResponse.class);
    }
}
