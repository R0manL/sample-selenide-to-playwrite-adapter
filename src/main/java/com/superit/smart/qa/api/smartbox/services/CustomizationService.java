package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.enums.UserModule;
import com.superit.smart.qa.api.smartbox.pojo.CockpitItem;
import com.superit.smart.qa.api.smartbox.pojo.customization.*;
import com.superit.smart.qa.api.smartbox.pojo.sharing_info.SharingInfo;
import com.superit.smart.qa.core.CustomCollectors;
import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.enums.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.api.smartbox.services.CockpitService.getItemBy;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.utils.FileUtils.getAbsolutePathForFileInResourceDir;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class CustomizationService {
    private static final String CUSTOMIZATION_ENDPOINT_PATH = "/webuiservice/api/webui/Customization";
    private static final String GET_CUSTOMIZATION_LIST = CUSTOMIZATION_ENDPOINT_PATH + "/TreeVersionLazy";
    private static final String GET_GROUP_TREE_VERSION = CUSTOMIZATION_ENDPOINT_PATH + "/GroupTreeVersion";

    private static final String RESOURCE_PATH = "settingsservice/api/user/settings/Customization";
    private static final String APPLY_CUSTOMIZATION_ENDPOINT = RESOURCE_PATH + "/link";
    private static final String USERBLOCK_ENDPOINT_PATH = "webuiservice/api/webui/View/userblock/";

    private static final String ATTRIBUTES = "Attributes";
    private static final String MENU_ID_REAL_ESTATE_ASSETS = "003";

    private CustomizationService() {
        //NONE
    }

    //NOTE: customResource = "" (in request) for all customizations except Dashboard customizations
    @NotNull
    public static List<CustomizationViewProfile> getCustomizationViewProfilesBy(
            @NotNull String itemName,
            @NotNull MenuModule module,
            @NotNull String customResource,
            @NotNull User user) {
        log.debug("API: Get list of available customizations for item: '{}' at '{}' module for '{}' user.",
                itemName, module.getCaption(), user.getUserName());
        final String itemSaasId = getItemBy(itemName, module, user).getSaasId();
        final CustomizationViewRequest customizationViewRequest = CustomizationViewRequest.builder()
                .filterString("")
                .customResource(customResource)
                .resource(itemSaasId)
                .skip(0)
                .take(1000).build();

        return setupBBRequestSpecFor(user)
                .basePath(GET_CUSTOMIZATION_LIST)
                .body(customizationViewRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(CustomizationViewResponse.class)
                .getCustomizations();
    }

    public static void updateCustomizationViewWith(@NotNull CustomizationViewProfile customizationProfile,
                                                   @NotNull User user) {
        log.info("API: Update customization '{}' for '{}' user.",
                customizationProfile.getNameEN(),
                user.getUserName());

        setupBBRequestSpecFor(user)
                .basePath(CUSTOMIZATION_ENDPOINT_PATH)
                .body(customizationProfile)
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    /**
     * Method read CustomizationViewProfile object from json file.
     *
     * @param relativeJSONPath relative path to JSON file. File should be located in
     * @return customization view profile object.
     */
    public static CustomizationViewProfile readCustomizationViewProfileFrom(@NotNull Path relativeJSONPath) {
        Path absResourceFilePath = getAbsolutePathForFileInResourceDir(relativeJSONPath);
        try {
            return new ObjectMapper().readValue(absResourceFilePath.toFile(), CustomizationViewProfile.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read '" + absResourceFilePath + "' file \n" + e.getMessage());
        }
    }

    public static void applyCustomizationViewProfileBy(@NotNull String itemName,
                                                       @NotNull UserModule userModule,
                                                       @NotNull MenuModule menuModule,
                                                       int customizationId,
                                                       @NotNull User user) {
        log.info("API: Apply customization with lid {} at module: '{}' for '{}' user.", customizationId, userModule.getModule(), user.getUserName());

        final String itemSaasId = getItemBy(itemName, menuModule, user).getSaasId();
        final LinkRequest linkRequest = buildLinkRequest(customizationId);

        setupBBRequestSpecFor(user)
                .basePath(APPLY_CUSTOMIZATION_ENDPOINT)
                .queryParam("args", userModule)
                .queryParam("args", itemSaasId)
                .queryParam("args", ENV_CONFIG.clientId())
                .body(linkRequest)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void applyCustomizationViewProfileBy(@NotNull String itemName,
                                                       @NotNull UserModule userModule,
                                                       @NotNull MenuModule menuModule,
                                                       @NotNull String dashboardView,
                                                       int customizationId,
                                                       @NotNull User user) {
        log.info("API: Apply customization with lid {} at module: '{}' on custom resource {} for '{}' user.",
                customizationId, userModule.getModule(), dashboardView, user.getUserName());

        final String itemSaasId = getItemBy(itemName, menuModule, user).getSaasId();
        final LinkRequest linkRequest = buildLinkRequest(customizationId);

        setupBBRequestSpecFor(user)
                .basePath(APPLY_CUSTOMIZATION_ENDPOINT)
                .queryParam("args", userModule)
                .queryParam("args", itemSaasId)
                .queryParam("args", ENV_CONFIG.clientId())
                .queryParam("args", dashboardView)
                .body(linkRequest)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static int getCustomizationIdBy(@NotNull String customizationName,
                                           @NotNull String itemName,
                                           @NotNull MenuModule module,
                                           @NotNull String customResource,
                                           @NotNull User user) {
        log.debug("API: Get '{}' customization id for item: '{}' at '{}' module for '{}' user.",
                customizationName,
                itemName,
                module.getCaption(),
                user.getUserName());

        return getCustomizationViewProfilesBy(itemName, module, customResource, user)
                .stream()
                .filter(customization -> customization.getNameEN().equals(customizationName))
                .collect(CustomCollectors.toSingleton())
                .getLid();
    }

    public static int createCustomizationView(CustomizationView customizationView, User user) {
        log.debug("API: Create customization view.");

        return setupBBRequestSpecFor(user)
                .basePath(CUSTOMIZATION_ENDPOINT_PATH)
                .body(customizationView)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(Integer.class);
    }

    public static GroupTreeVersion getGroupTreeVersionFor(@NotNull String itemName,
                                                          @NotNull MenuModule module,
                                                          @NotNull User user) {
        log.debug("API: Get group tree for item: '{}', module: '{}' for user: '{}'.",
                itemName, module.getCaption(), user.getUserName());
        final String itemSaasId = getItemBy(itemName, module, user).getSaasId();
        final GroupTreeVersionRequest groupTreeVersionRequest = buildGroupTreeVersionRequest(itemSaasId, false);

        return getGroupTreeVersion(groupTreeVersionRequest, user);
    }

    private static GroupTreeVersion getGroupTreeVersion(
            @NotNull GroupTreeVersionRequest groupTreeVersionRequest,
            @NotNull User user) {

        return setupBBRequestSpecFor(user)
                .basePath(GET_GROUP_TREE_VERSION)
                .body(groupTreeVersionRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(GroupTreeVersion.class);
    }

    private static LinkRequest buildLinkRequest(int customizationId) {
        return LinkRequest.builder()
                .value(customizationId)
                .build();
    }

    private static GroupTreeVersionRequest buildGroupTreeVersionRequest(@NotNull String resource, boolean isDashboard) {
        return GroupTreeVersionRequest.builder()
                .dataLevel("Property")
                .isDashboard(isDashboard)
                .resource(resource)
                .build();
    }

    public static void deleteCustomizationViewProfile(int lid,
                                                      @NotNull User user) {
        log.debug("API: Delete customization view profile with id {}.", lid);

        setupBBRequestSpecFor(user)
                .basePath(CUSTOMIZATION_ENDPOINT_PATH + "/" + lid)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static SharingInfo getCustomizationSharingInfoBy(int customizationId, User user) {
        log.info("API: Get shared info for customization view with id: '{}' for: '{}' user.",
                customizationId, user.getUserName());
        return SharingInfoService.getSharingInfoFor(customizationId, SharingInfoService.SharingLevel.CUSTOMIZATION, user);
    }

    public static void updateCustomizationSharingInfoWith(@NotNull SharingInfo newCustomizationSharedInfo, User user) {
        log.info("API: Updating shared information for customization view with id: '{}' for: '{}' user.",
                newCustomizationSharedInfo.getId(), user);

        SharingInfoService.updateSharingInfoFor(
                newCustomizationSharedInfo,
                SharingInfoService.SharingLevel.CUSTOMIZATION,
                user);
    }

    private static UserSettingsSetup createMasterDataUserBlockSettingsWithAttributesAvailability(@NotNull User user) {
        List<DataUserBlock> dataUserBlocks = getMasterDataUserBlockSettings(user);
        // filter attribute block
        DataUserBlock attribute = dataUserBlocks.stream()
                .filter(dataUserBlock -> dataUserBlock.getBlockName().equals(ATTRIBUTES))
                .collect(CustomCollectors.toSingleton());

        // remove original attribute block form the list
        List<DataUserBlock> updatedDataUserBlocks = dataUserBlocks.stream()
                .filter(dataUserBlock -> !dataUserBlock.getBlockName().equals(ATTRIBUTES))
                .collect(Collectors.toList());

        // update attribute block
        DataUserBlock updatedAttributeBlock = attribute
                .toBuilder()
                .enabled(true).build();

        // add updated attribute block
        updatedDataUserBlocks.add(updatedAttributeBlock);

        UserSettings userSettings = UserSettings
                .builder()
                .menuId(MENU_ID_REAL_ESTATE_ASSETS)
                .settings(updatedDataUserBlocks)
                .build();

        return UserSettingsSetup.builder().userSettings(userSettings).build();
    }

    private static UserSettingsSetup generateUserBlockSettingsAndAdd(BlockName newBlockName, @NotNull String itemName, MenuModule module, @NotNull User user) {
        // Get saasId.
        CockpitItem item = getItemBy(itemName, module, user);
        String saasId = item.getSaasId();
        List<DataUserBlock> dataUserBlocks = getUserBlockSettingsFor(saasId, user);

        // filter attribute block
        DataUserBlock attribute = dataUserBlocks.stream()
                .filter(dataUserBlock -> newBlockName.getName().equals(dataUserBlock.getBlockName()))
                .collect(CustomCollectors.toSingleton());

        // remove original attribute block form the list
        List<DataUserBlock> updatedDataUserBlocks = dataUserBlocks.stream()
                .filter(dataUserBlock -> !newBlockName.getName().equals(dataUserBlock.getBlockName()))
                .collect(Collectors.toList());

        // update attribute newBlock
        DataUserBlock updatedAttributeBlock = attribute
                .toBuilder()
                .enabled(true)
                .build();

        // add updated attribute block
        updatedDataUserBlocks.add(updatedAttributeBlock);

        UserSettings userSettings = UserSettings
                .builder()
                .menuId(saasId)
                .settings(updatedDataUserBlocks)
                .build();

        return UserSettingsSetup.builder()
                .userSettings(userSettings)
                .build();
    }

    private static List<DataUserBlock> getMasterDataUserBlockSettings(@NotNull User user) {
        log.debug("API: Get Master Data User Block settings");

        return Arrays.stream(setupBBRequestSpecFor(user)
                .basePath(USERBLOCK_ENDPOINT_PATH + MENU_ID_REAL_ESTATE_ASSETS)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(DataUserBlock[].class))
                .toList();
    }

    private static List<DataUserBlock> getUserBlockSettingsFor(String saasId, @NotNull User user) {
        log.debug("API: Get Master Data User Block settings");

        return Arrays.stream(setupBBRequestSpecFor(user)
                        .basePath(USERBLOCK_ENDPOINT_PATH + saasId)
                        .when()
                        .get()
                        .then()
                        .statusCode(HTTP_OK)
                        .extract()
                        .as(DataUserBlock[].class))
                .toList();
    }

    private static void updateMasterDataUserBlockSettings(@NotNull User user, @NotNull UserSettingsSetup userSettingsSetup) {
        log.debug("API: Update Master Data User Block settings");

        setupBBRequestSpecFor(user)
                .basePath(USERBLOCK_ENDPOINT_PATH)
                .when()
                .body(userSettingsSetup)
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    @Getter
    public enum BlockName {
        ATTRIBUTES("Attributes"),
        DISCOUNT_PREMIUM_DETAIL("Discount/premium detail"),
        MAINTENANCE_DETAILS("Maintenance details"),
        REPORTING_SYSTEM("Reporting system"),
        TECHNICAL_IDS("Technical IDs");

        private final String name;

        BlockName(@NotNull String name) {
            this.name = name;
        }
    }
}
