package com.superit.smart.qa.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeysUtils {
    private static final String OS_NAME = System.getProperty("os.name");

    public static final String CTRL_KEY = OS_NAME.equalsIgnoreCase("mac") ? "Meta" : "Control";


    private KeysUtils() {
        //NONE
    }
}
