package com.i4o.dms.kubota.warranty.pcr.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FailurePartsDetailsDto {
		private String code;
		private String description;
	 	private Long id;
	 	private String value;
}
