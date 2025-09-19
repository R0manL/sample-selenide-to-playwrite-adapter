package com.superit.smart.qa.api.smartbox.pojo.sharing_info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {
    private String name;
    private Integer usersCount;
    private List<User> users;
}
