package com.superit.smart.qa.db.smartbox.enums;

import com.superit.smart.qa.ui.smartbox.pages.CustomizeCalculation.ShowValues;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;


/**
 * Created by R0manL.
 */

public enum FormatDB {
    K("t"),
    M("mio"),
    NO_FORMAT("db");

    private final String dbValue;

    FormatDB(@NotNull String dbValue) {
        this.dbValue = dbValue;
    }

    public static FormatDB getFormatDB(@NotNull String dbValue) {
        for (FormatDB value : values()) {
            if (value.getDBValue().equals(dbValue)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Can't find 'FormatDB' by dbValue: '" + dbValue + "'.");
    }

    @NotNull
    public String getDBValue() {
        return this.dbValue;
    }

    public ShowValues toShowValuesUI() {
        EnumMap<FormatDB, ShowValues> converter = new EnumMap<>(FormatDB.class);
        converter.put(K, ShowValues.K);
        converter.put(M, ShowValues.M);
        converter.put(NO_FORMAT, ShowValues.NO_FORMAT);

        return converter.get(this);
    }
}
