package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
public class RoleSearchDto {

    private String roleCode;

    private String roleName;

    private Integer page;

    private Integer size;
    
    private String isActive;
    
    private String applicableFor;

}
