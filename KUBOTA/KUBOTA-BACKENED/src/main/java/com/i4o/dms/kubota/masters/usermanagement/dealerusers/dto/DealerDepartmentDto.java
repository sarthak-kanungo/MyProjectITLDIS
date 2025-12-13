package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealerDepartmentDto {

    private String departmentCode;
    
    private String departmentName;

    private Integer page;

    private Integer size;
}
