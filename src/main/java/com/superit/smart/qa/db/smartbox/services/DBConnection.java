package com.superit.smart.qa.db.smartbox.services;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;

public interface DBConnection {

    static Jdbi createConnection() {
        return Jdbi
                .create(ENV_CONFIG.dbUrl(), ENV_CONFIG.dbLogin(), ENV_CONFIG.dbPassword())
                .installPlugin(new SqlObjectPlugin());
    }
}
