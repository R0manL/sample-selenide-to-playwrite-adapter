package com.superit.smart.qa.db.smartbox.services;

import com.superit.smart.qa.db.smartbox.dao.CustomizationDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.superit.smart.qa.db.smartbox.services.DBConnection.createConnection;

@Slf4j
public class CustomizationDBService {

    private CustomizationDBService() {
        //None
    }

    public static List<String> getCustomizationSubportfolioValues() {
        return createConnection()
                .onDemand(CustomizationDAO.class)
                .getCustomizationSubportfolioValues();
    }

    public static List<String> getCustomizationTypeOfUseValues() {
        return createConnection()
                .onDemand(CustomizationDAO.class)
                .getCustomizationTypeOfUseValues();
    }
}
