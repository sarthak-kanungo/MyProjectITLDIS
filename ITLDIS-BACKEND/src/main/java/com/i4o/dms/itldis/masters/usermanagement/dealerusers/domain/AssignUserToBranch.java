package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;

import java.util.Date;
import java.util.List;

import com.i4o.dms.itldis.spares.picklist.domain.PickListItemDtl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AssignUserToBranch {
	private Long id;
	private List<AssignUserToBranchMaster> userToBranch;

}
