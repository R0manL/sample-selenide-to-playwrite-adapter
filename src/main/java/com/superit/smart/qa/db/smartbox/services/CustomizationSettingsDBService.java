package com.superit.smart.qa.db.smartbox.services;

import com.superit.smart.qa.db.smartbox.dao.UserSettingsDAO;
import com.superit.smart.qa.db.smartbox.pojo.MasterDataCalculationCustomizationDB;
import lombok.extern.slf4j.Slf4j;

import static com.superit.smart.qa.db.smartbox.services.DBConnection.createConnection;
import static com.superit.smart.qa.db.smartbox.utils.StringUtils.getObjectFrom;

@Slf4j
public class CustomizationSettingsDBService {

    private CustomizationSettingsDBService() {
        // None
    }

    public static MasterDataCalculationCustomizationDB getDefaultMasterDataCalculationCustomizationSettings() {
        log.info("Get Master Data Calculation Customization Settings");
        String settingsAsString = createConnection()
                .onDemand(UserSettingsDAO.class)
                .getMasterDataCalculationCustomizationSettings();

        return getObjectFrom(settingsAsString, MasterDataCalculationCustomizationDB.class);
    }
}