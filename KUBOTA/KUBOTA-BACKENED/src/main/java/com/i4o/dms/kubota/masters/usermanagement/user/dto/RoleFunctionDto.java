package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import java.util.List;

import com.i4o.dms.kubota.masters.usermanagement.user.domain.RoleMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleFunctionDto {

    private RoleMaster roleMaster;
    private List<Long> functionalityMasters;
    private Long assignedBy;

}
