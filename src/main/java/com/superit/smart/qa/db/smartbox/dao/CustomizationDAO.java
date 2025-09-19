package com.superit.smart.qa.db.smartbox.dao;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface CustomizationDAO {

    @SqlQuery("use SMARTDB\n" +
            "select xkeyalphanr, xkeyMC_Eng, " +
            "xkeymc from xlbikeyval where lparentid =(select lid " +
            "from xlbikeyhead where xkeyhmc='SubPortfolio1') " +
            "and ldelete=0 and clientid=-1")
    List<String> getCustomizationSubportfolioValues();

    @SqlQuery("use SMARTDB\n" +
            "select xkeyalphanr, xkeyMC_Eng, " +
            "xkeymc from xlbikeyval " +
            "where lparentid =(select lid from xlbikeyhead where xkeyhmc='Flaechenarten') " +
            "and ldelete=0 and clientid=-1")
    List<String> getCustomizationTypeOfUseValues();
}
