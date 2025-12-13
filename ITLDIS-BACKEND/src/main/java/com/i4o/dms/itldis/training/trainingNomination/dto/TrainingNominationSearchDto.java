package com.i4o.dms.itldis.training.trainingNomination.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingNominationSearchDto {
	private Long programId;
	private Long nomineeId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String toDate;
	private Integer page;
	private Integer size;

}
