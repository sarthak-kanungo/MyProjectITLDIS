package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.dto;

import lombok.Data;

@Data
public class TranHierSearchRequestDto {
	
	private String transName;
//	private String finalApp;
	private Integer page;
	private Integer size;

}
