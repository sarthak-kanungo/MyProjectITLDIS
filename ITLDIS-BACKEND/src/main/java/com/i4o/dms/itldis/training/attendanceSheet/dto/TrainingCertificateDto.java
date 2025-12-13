package com.i4o.dms.itldis.training.attendanceSheet.dto;

import lombok.Data;

@Data
public class TrainingCertificateDto {
	
	private Integer programId;
	
	private Integer employeeId;
	
	private Integer dealerId;
	
	private String printStatus;

}
