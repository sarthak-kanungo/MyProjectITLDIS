package com.i4o.dms.kubota.training.attendanceSheet.dto;


import java.util.List;

import com.i4o.dms.kubota.training.attendanceSheet.domain.NomineAttendanceSheet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TrainingAttendanceSheetDto {
	
	private Long programId;
	private Long trainer1;
	private Long trainer2;
	private float avgGrowthIndex;
	List<NomineAttendanceSheet> attendanceDtl;
	List<NominationEmpIndex> nominationEmpIndexs;

}
