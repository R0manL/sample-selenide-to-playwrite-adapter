package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class HardDeleteRequest {
    private String menuId;
    private NavigationBarFilter navigationBarFilter;
    private List<Integer> excludedLids = new ArrayList<>();
    private List<Integer> idsToDelete;
    private int hardDeleteParam;

    @Getter
    @Setter
    @Builder
    public static class NavigationBarFilter {
        private String searchValue = "";
        private List<String> propertyNames = new ArrayList<>();
    }
}
