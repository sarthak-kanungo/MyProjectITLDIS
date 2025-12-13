package com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentSearchDto {
    private String departmentCode;

    private String departmentName;

    private String linkedToDealer;

    private String dealerCode;

    private Integer page;

    private Integer size;

}
