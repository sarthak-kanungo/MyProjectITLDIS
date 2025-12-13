package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionPermissionDto {

    private Long functionalityId;

    private Long permissionId;

    private Boolean applicableToKubota = false;

    private Boolean applicableToDealer = false;

    private Integer seqNo;

    private String routerLink;

}
