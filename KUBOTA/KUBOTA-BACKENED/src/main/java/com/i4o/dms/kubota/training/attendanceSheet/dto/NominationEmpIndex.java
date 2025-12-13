package com.i4o.dms.kubota.training.attendanceSheet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NominationEmpIndex {
	
	private Long programNominationDtlId;

	private float preTest;

	private float postTest;

	private float growthIndex;

}
