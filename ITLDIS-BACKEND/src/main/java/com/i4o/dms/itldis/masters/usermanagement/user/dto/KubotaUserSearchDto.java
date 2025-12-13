package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class KubotaUserSearchDto {
	private String employeeCode;

    private String employeeName;

    private Integer page;

    private Integer size;
}
