package com.i4o.dms.itldis.training.trainingProgrammeCalendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainingProgCalendarSearchDto {
	private String departmentName;
	private String programNo;
	private Long trainingLocationId;
	private Long trainingModuleId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String toDate;
	private Integer page;
	private Integer size;
}
