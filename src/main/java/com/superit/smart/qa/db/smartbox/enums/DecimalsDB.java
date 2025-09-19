package com.superit.smart.qa.db.smartbox.enums;


import com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation.Decimals;

import java.util.EnumMap;

/**
 * Created by R0manL.
 */

public enum DecimalsDB {
    WITHOUT_DECIMALS(0),
    WITH_1_DECIMAL(1),
    WITH_2_DECIMALS(2);

    private final int id;


    DecimalsDB(int id) {
        this.id = id;
    }

    public static DecimalsDB getDecimalsDB(int id) {
        for (DecimalsDB value : values()) {
            if (value.getId() == id) {
                return value;
            }
        }

        throw new IllegalArgumentException("Can't find 'DecimalsDB' by dbValue: '" + id + "'.");
    }

    public int getId() {
        return this.id;
    }

    public Decimals toDecimalsUI() {
        EnumMap<DecimalsDB, Decimals> converter = new EnumMap<>(DecimalsDB.class);
        converter.put(WITHOUT_DECIMALS, Decimals.WITHOUT_DECIMALS);
        converter.put(WITH_1_DECIMAL, Decimals.WITH_1_DECIMAL);
        converter.put(WITH_2_DECIMALS, Decimals.WITH_2_DECIMALS);

        return converter.get(this);
    }
}
