package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by R0manL.
 */


@Data
public class UserAPISession {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("scope")
    private String scope;
    private LocalDateTime expiresAt;
    @JsonProperty("ext_expires_in")
    private String extExpiresIn;
}
