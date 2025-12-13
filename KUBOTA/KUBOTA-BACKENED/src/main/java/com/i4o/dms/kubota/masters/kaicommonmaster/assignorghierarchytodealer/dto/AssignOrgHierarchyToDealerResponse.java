package com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "action","id", "dealerCode", "dealerName", "departmentName", "hierarchyCode", "hierarchyDesc" })

public interface AssignOrgHierarchyToDealerResponse {

	Long getId();

	String getDealerCode();

	String getDealerName();

	String getDepartmentName();

	String getHierarchyCode();

	String getHierarchyDesc();

	String getAction();
	
	Long getOrgHierarchyId();
	Long getHoDepartmentId();

	@JsonIgnore
	Long getCount();

}
