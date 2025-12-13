package com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto;

import java.util.List;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMasterOrgHier;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeOrgHierDto {
	public Long hoEmployeeId;
	public List<KubotaEmployeeMasterOrgHier> saveOrgHier;
//	public List<KubotaEmployeeMasterOrgHier> marketing;
//	public List<KubotaEmployeeMasterOrgHier> service;
//	public List<KubotaEmployeeMasterOrgHier> spares;

}
