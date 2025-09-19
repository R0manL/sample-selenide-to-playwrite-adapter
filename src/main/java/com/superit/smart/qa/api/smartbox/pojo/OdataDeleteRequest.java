package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OdataDeleteRequest {
    List<DeleteRequest> requests;
}
