package com.i4o.dms.itldis.security.dto;

import lombok.Data;

@Data
public class LoggedInUserDetailsResDto {
    
	private Long loginId;
    
	private Long branchId;

	private Long dealerId;

	private String username;

	private Long kubotaEmployeeId;

	private Long dealerEmployeeId;

}
