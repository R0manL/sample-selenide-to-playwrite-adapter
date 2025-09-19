package com.superit.smart.qa.api.smartbox.pojo.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class PropertyVal {
    @NotNull
    private String propertyName;
    private Object propertyValue;
    private String propertyNameForReview;

    public static PropertyVal createValue(String name, String value) {
        return PropertyVal.builder()
                .propertyName(name)
                .propertyValue(value)
                .propertyNameForReview("")
                .build();
    }

    public static PropertyVal createValue(String name, boolean value) {
        return PropertyVal.builder()
                .propertyName(name)
                .propertyValue(value)
                .propertyNameForReview("")
                .build();
    }

    public static PropertyVal createValue(String name, int value) {
        return PropertyVal.builder()
                .propertyName(name)
                .propertyValue(value)
                .propertyNameForReview("")
                .build();
    }

    public static PropertyVal createValue(String name) {
        return PropertyVal.builder()
                .propertyName(name)
                .propertyNameForReview("")
                .build();
    }
}
