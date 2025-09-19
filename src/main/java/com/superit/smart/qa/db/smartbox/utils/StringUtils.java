package com.superit.smart.qa.db.smartbox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

public class StringUtils {

    private StringUtils() {
        //None
    }

    public static <T> T getObjectFrom(@NotNull String objectAsString, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(objectAsString, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Can not create object from string " + objectAsString + ". Error message: " + e.getMessage());
        }
    }
}
