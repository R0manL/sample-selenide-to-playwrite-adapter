package com.superit.smart.qa.core.enums;

/* Created by R0manL. */


import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.utils.StringUtils.getUsernameFromEmail;

/**
 * Created by R0manL.
 */

@Getter
public enum User {
    DEFAULT_USER(ENV_CONFIG.user1Email(), ENV_CONFIG.user1Password(), ENV_CONFIG.user1UserId()),
    USER1(DEFAULT_USER.getEmail(), DEFAULT_USER.getPassword(), DEFAULT_USER.getId()),
    USER2(ENV_CONFIG.user2Email(), ENV_CONFIG.user2Password(), ENV_CONFIG.user2UserId()),
    USER3(ENV_CONFIG.user3Email(), ENV_CONFIG.user3Password(), ENV_CONFIG.user3UserId()),
    USER4(ENV_CONFIG.user4Email(), ENV_CONFIG.user4Password(), ENV_CONFIG.user4UserId());

    private final String email;
    private final String password;
    private final int id;

    User(@NotNull String email, @NotNull String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    @NotNull
    public String getUserName() {
        return getUsernameFromEmail(getEmail());
    }
}