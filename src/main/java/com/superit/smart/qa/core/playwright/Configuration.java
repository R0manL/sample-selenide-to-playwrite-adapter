package com.superit.smart.qa.core.playwright;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;

public class Configuration {

    public static final boolean HEADLESS = false;
    public static final boolean SAVE_TRACES = true;
    public static final boolean SCREENSHOT = true;
    public static final boolean SNAPSHOT = true;
    public static final boolean SOURCES = false;

    /* Timeouts */
    public static final double DEFAULT_TIMEOUT = ENV_CONFIG.webElmLoadDuration().toMillis();
    public static final double SLOW_MOTION_DELAY = 100.0;
    public static final double DOWNLOAD_TIMEOUT = ENV_CONFIG.webElmLoadDuration().multipliedBy(8).toMillis() ;

    /* Paths */
    public static final String ATTACHMENT_DIR = System.getProperty("user.dir") + "/target/surefire-reports/";
    public static final String DOWNLOAD_DIR = System.getProperty("user.dir") + "/target/downloads/";
    public static final String OWNER_DATA_FILE_PATH = ATTACHMENT_DIR + "tests-owner-data.csv";

    /* Browser settings */
    public static final String BASE_URL = ENV_CONFIG.smartboxWebUrl(); // Null by default
    public static final double BROWSER_TO_START_TIMEOUT = 40000.0;
    public static final int BROWSER_WIDTH = 1920;
    public static final int BROWSER_HEIGHT = 1080;

    private Configuration() {
     // NONE
    }
}
