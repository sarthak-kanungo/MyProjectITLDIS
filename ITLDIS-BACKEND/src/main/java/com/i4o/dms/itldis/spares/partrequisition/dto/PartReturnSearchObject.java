package com.i4o.dms.itldis.spares.partrequisition.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartReturnSearchObject {
//    private String returnNo;
	private String partReturnNo;
    private String requisitionNo;
    private String jobCardNo;
    private String requisitionPurpose;
    private String reasonForReturn;
    private String requisitionFromDate;
    private String requisitionToDate;
//    private String jobCardFromDate;
//    private String jobCardToDate;
    private String issueFromDate;
    private String issueToDate;
    private Integer page;
    private Integer size;
}
