package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DynamicMenuResponse {
    String functionality;

    Long Id;

    String routerLink;

    Integer sequenceNo;

    Long parentId;
    List<DynamicMenuResponse> children = new ArrayList<>();

}
