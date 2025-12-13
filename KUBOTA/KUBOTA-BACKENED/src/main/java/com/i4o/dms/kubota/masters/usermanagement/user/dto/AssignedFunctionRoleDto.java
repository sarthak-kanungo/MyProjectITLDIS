package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignedFunctionRoleDto {
    private Long id;
    private String tabName;
    private List<Map<String,Object>> functionalityMasters;

    private String functionality;
    private Boolean assignedFlag;

    private List<Map<String,Object>> permissions;
}
