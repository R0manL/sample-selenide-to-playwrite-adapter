package com.superit.smart.qa.utils;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class ListUtils {
    private final Random random = new SecureRandom();

    @NotNull
    public String getRandomFrom(List<String> values) {
        int randomIndex = this.random.nextInt(values.size());
        return values.get(randomIndex);
    }
}
