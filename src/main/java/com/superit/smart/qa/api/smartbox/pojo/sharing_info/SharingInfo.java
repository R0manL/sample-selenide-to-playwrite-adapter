package com.superit.smart.qa.api.smartbox.pojo.sharing_info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class SharingInfo {
    private final int id;
    private final List<Integer> sharedUsers;
    private final List<String> sharedGroups;
    private final List<User> users;
    private final List<Group> groups;
}
