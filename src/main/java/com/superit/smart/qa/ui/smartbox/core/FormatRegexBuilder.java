package com.superit.smart.qa.ui.smartbox.core;

/* Created by R0manL. */

import com.superit.smart.qa.db.smartbox.enums.DecimalsDB;
import com.superit.smart.qa.db.smartbox.enums.FormatDB;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;


//ToDo ad other 'hardcoded' regexps in the code to this class
public class FormatRegexBuilder {
    private String start = "";
    private String mid = "";
    private String end = "";


    public FormatRegexBuilder startsWithNumber() {
        this.start = "^-?(\\d{1,3}(,\\d{3})*)";
        return this;
    }

    public FormatRegexBuilder startsWithString() {
        this.start = "^\\S+";
        return this;
    }

    public FormatRegexBuilder hasOneDigit() {
        this.mid = "(.\\d{1})";
        return this;
    }

    public FormatRegexBuilder hasTwoDigits() {
        this.mid = "(.\\d{2})";
        return this;
    }

    public FormatRegexBuilder hasThreeDigits() {
        this.mid = "(.\\d{3})";
        return this;
    }

    public FormatRegexBuilder has(DecimalsDB decimalsDB) {
        switch (decimalsDB) {
            case WITHOUT_DECIMALS:
                this.start = "";
                break;
            case WITH_1_DECIMAL:
                hasOneDigit();
                break;
            case WITH_2_DECIMALS:
                hasTwoDigits();
                break;
            default:
                throw new InvalidParameterException("Unrecognized 'DecimalsDB': '" + decimalsDB + "'.");
        }

        return this;
    }

    public FormatRegexBuilder endWithPercentSymbol() {
        this.end = "\\%$";
        return this;
    }

    public FormatRegexBuilder endWithK() {
        this.end = "\\sK$";
        return this;
    }

    public FormatRegexBuilder endWithM() {
        this.end = "\\sM$";
        return this;
    }

    public FormatRegexBuilder endWith(FormatDB formatDB) {
        switch (formatDB) {
            case K:
                endWithK();
                break;
            case M:
                endWithM();
                break;
            case NO_FORMAT:
                this.end = "";
                break;
            default:
                throw new InvalidParameterException("Unrecognized 'FormatDB': '" + formatDB + "'.");
        }

        return this;
    }

    @NotNull
    public String getRegex() {
        return this.start + this.mid + this.end;
    }
}
