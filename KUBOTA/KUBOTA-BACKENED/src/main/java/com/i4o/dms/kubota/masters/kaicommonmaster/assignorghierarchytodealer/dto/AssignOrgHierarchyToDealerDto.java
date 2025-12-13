package com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignOrgHierarchyToDealerDto {

	private Long dealerId;

	private Long departmentId;

	private Long orgHierarchyId;
	
	private Integer page;
	
	private Integer size;

}
