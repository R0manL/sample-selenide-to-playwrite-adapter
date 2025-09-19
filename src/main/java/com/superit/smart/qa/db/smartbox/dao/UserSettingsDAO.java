package com.superit.smart.qa.db.smartbox.dao;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface UserSettingsDAO {
    @SqlQuery("select zresSettings from SMARTDB.dbo.zResultPresentation " +
            "where lid in (SELECT TYPE5 FROM BISDSYSDB.dbo.TUSERSETTINGS where ident = 'ResultPresentation_smart-data_003_tblPreview_0' AND UserID = 636)")
    String getMasterDataCalculationCustomizationSettings();
}
