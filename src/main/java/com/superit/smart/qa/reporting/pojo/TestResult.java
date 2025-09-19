package com.superit.smart.qa.reporting.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@ToString
public class TestResult {
    @NotNull
    private final String className;
    @NotNull
    private final String displayName;
    @NotNull
    private final String owner;
}