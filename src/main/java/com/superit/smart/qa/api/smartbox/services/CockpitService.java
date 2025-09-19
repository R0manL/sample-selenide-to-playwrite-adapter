package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.Cockpit;
import com.superit.smart.qa.api.smartbox.pojo.CockpitItem;
import com.superit.smart.qa.api.smartbox.pojo.sharing_info.SharingInfo;
import com.superit.smart.qa.core.CustomCollectors;
import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.api.smartbox.services.SharingInfoService.SharingLevel;
import static com.superit.smart.qa.api.smartbox.services.TopMenuService.getTopMenuModuleId;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by R0manL on 09.06.2021.
 */

@Slf4j
public class CockpitService {
    private static final String COCKPIT_ENDPOINT_PATH = "/webuiservice/api/webui/view/cockpit";
    private static final String ITEMS_ENDPOINT_PATH = COCKPIT_ENDPOINT_PATH + "/group/items";
    private static final String GLOBAL_COCKPIT_OWNER = "Global";
    private static final String CLIENT_COCKPIT_OWNER = "Client specific";
    private static final String MODULE_ID_QUERY_PARAM = "moduleId";
    private static final String GROUP_ID_QUERY_PARAM = "groupId";

    private CockpitService() {
        // None
    }

    public static void createCockpit(Cockpit cockpit, MenuModule module, User user) {
        log.info("API: Create '{}' cockpit for '{}' module.", cockpit.getName(), module.getCaption());
        int moduleId = getTopMenuModuleId(module, user);

        setupBBRequestSpecFor(user)
                .basePath(COCKPIT_ENDPOINT_PATH)
                .queryParam(MODULE_ID_QUERY_PARAM, moduleId)
                .body(cockpit)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void addItemToCockpit(MenuModule module, @NotNull String cockpitName, @NotNull String itemName, User user) {
        log.info("API: Adding item: '{}' to cockpit: '{}' in module: '{}' for '{}' user.", itemName, cockpitName, module, user.getUserName());
        int moduleId = getTopMenuModuleId(module, user);
        int cockpitId = getCockpitIdBy(cockpitName, module, user);
        int itemId = getItemIdBy(itemName, module, user);

        final String itemJson = "[{'id' : " + itemId + ", 'orderInGroup': 0}]";
        setupBBRequestSpecFor(user)
                .basePath(ITEMS_ENDPOINT_PATH)
                .queryParam(MODULE_ID_QUERY_PARAM, moduleId)
                .queryParam(GROUP_ID_QUERY_PARAM, cockpitId)
                .body(itemJson)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }

    public static List<Cockpit> getGlobalCockpitsFor(MenuModule module, User user) {
        return getAllCockpitsWithOwnerFor(module, user).stream()
                .filter(cockpit -> GLOBAL_COCKPIT_OWNER.equals(cockpit.getOwner()))
                .collect(Collectors.toList());
    }

    public static List<Cockpit> getClientCockpitsFor(MenuModule module, User user) {
        return getAllCockpitsFor(module, user).stream()
                .filter(cockpit -> CLIENT_COCKPIT_OWNER.equals(cockpit.getOwner()))
                .collect(Collectors.toList());
    }

    public static Cockpit getCockpitBy(@NotNull String cockpitName, MenuModule module, User user) {
        log.info("API: Getting '{}' cockpit for module: '{}' and user: '{}'.", cockpitName, module, user.getUserName());

        return getAllCockpitsWithOwnerFor(module, user).stream()
                .filter(cockpit -> cockpitName.equals(cockpit.getName()))
                .collect(CustomCollectors.toSingleton());
    }

    public static CockpitItem getItemBy(@NotNull String name, MenuModule module, User user) {
        return getAllItemsFor(module, user).stream()
                .filter(item -> name.equals(item.getName()))
                .collect(CustomCollectors.toSingleton());
    }

    public static int getCockpitIdBy(@NotNull String cockpitName, MenuModule module, User user) {
        log.info("API: Getting '{}' cockpit's ID for module: '{}' and user: '{}'.",
                cockpitName, module, user.getUserName());
        return getCockpitBy(cockpitName, module, user).getId();
    }

    public static SharingInfo getCockpitSharingInfoFor(@NotNull String cockpitName,
                                                       @NotNull MenuModule module,
                                                       User user) {
        log.info("API: Get shared info for cockpit: '{}' at module: '{}' for '{}' user.",
                cockpitName, module, user.getUserName());
        int cockpitId = getCockpitIdBy(cockpitName, module, user);

        return SharingInfoService.getSharingInfoFor(cockpitId, SharingLevel.COCKPIT, user);
    }

    /**
     * Getting all cockpits that has an owner (displayed on UI).
     *
     * @param module (menu item)
     * @return list of cockpits
     */
    public static List<Cockpit> getAllCockpitsWithOwnerFor(MenuModule module, User user) {
        log.info("API: Get all cockpits that have owner.");
        return getAllCockpitsFor(module, user).stream()
                .filter(cockpit -> cockpit.getOwner() != null)
                .collect(Collectors.toList());
    }

    public static List<Cockpit> getAllCockpitsFor(MenuModule module, User user) {
        log.info("API: Get a list of cockpits for '{}' module.", module);
        int moduleId = getTopMenuModuleId(module, user);

        return Arrays.stream(setupBBRequestSpecFor(user)
                .basePath(COCKPIT_ENDPOINT_PATH)
                .queryParam(MODULE_ID_QUERY_PARAM, moduleId)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(Cockpit[].class))
                .toList();
    }

    public static void updateCockpitSharingInfoFor(@NotNull MenuModule module,
                                                   @NotNull SharingInfo cockpitSharedInfo,
                                                   User user) {
        log.info("API: Updating shared cockpit information at module: '{}' for: '{}' user.", module, user.getUserName());
        SharingInfoService.updateSharingInfoFor(cockpitSharedInfo, SharingLevel.COCKPIT, user);
    }

    public static void deleteCockpit(Cockpit cockpit, MenuModule module, User user) {
        log.info("API: Delete cockpit: '{}' in module '{}'.", cockpit.getName(), module);
        int moduleId = getTopMenuModuleId(module, user);
        deleteCockpit(cockpit.getId(), moduleId, user);
    }

    /**
     * Delete cockpit.
     *
     * @param groupId  - cockpit's ID.
     * @param moduleId - module (menu) which cockpit related to.
     */
    public static void deleteCockpit(int groupId, int moduleId, User user) {
        log.info("API: Delete cockpit: '{}' in module: '{}'.", groupId, moduleId);

        setupBBRequestSpecFor(user)
                .basePath(COCKPIT_ENDPOINT_PATH)
                .queryParam(MODULE_ID_QUERY_PARAM, moduleId)
                .queryParam(GROUP_ID_QUERY_PARAM, groupId)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_NO_CONTENT);
    }

    public static List<CockpitItem> getAllItemsFor(MenuModule module, User user) {
        log.info("API: Getting all items for '{}' module.", module);
        final int GROUP_WITH_ALL_ITEMS_ID = -1;

        return getAllCockpitsFor(module, user).stream()
                .filter(c -> c.getId() == GROUP_WITH_ALL_ITEMS_ID)
                .flatMap(cockpit -> cockpit.getItems().stream())
                .toList();
    }

    private static int getItemIdBy(@NotNull String name, MenuModule module, User user) {
        return getItemBy(name, module, user).getId();
    }
}
