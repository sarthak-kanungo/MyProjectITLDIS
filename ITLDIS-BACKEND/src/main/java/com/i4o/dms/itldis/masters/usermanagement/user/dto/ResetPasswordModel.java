package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordModel {

	private String oldPassword;
	private String newPassword;
	private Integer count;
	
}
