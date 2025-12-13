package com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto;

import java.util.List;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.LoginUser;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.UserFunctionMapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DealerSaveDataDto {
	private LoginUser loginUser;
	private List<UserFunctionMapping> userRoleManu;

}
