package com.i4o.dms.itldis.masters.crm.questionMaster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class QuestionMasterSearchDto {
	private String questionType;
	private Long questionId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String fromDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String toDate;
	private Integer page;
	private Integer size;

}
