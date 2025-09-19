package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class CleanCacheResponse {
    private final boolean isSucess;
    private final String message;
}