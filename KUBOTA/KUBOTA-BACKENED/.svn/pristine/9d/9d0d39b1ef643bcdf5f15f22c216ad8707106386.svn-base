package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "action","id", "roleCode", "roleName", "menuName", "roleActive", "menuActive","applicableTo" })
@JsonInclude(value=Include.NON_NULL)
public interface RoleSearchResponse {

	Long getId();

	String getRoleCode();

	String getRoleName();

	String getMenuName();

	String getRoleActive();

	String getMenuActive();
	
	String getApplicableTo();
	
	@JsonIgnore
    Long getTotalRecords();
	
	String getAction();

}
