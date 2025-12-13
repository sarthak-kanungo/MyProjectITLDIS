package com.i4o.dms.kubota.spares.partrequisition.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitionSearchObject {
    private String requisitionNo;
    private String jobCardNo;
    private String requisitionPurpose;
    private String fromDate;
    private String toDate;
    private Integer page;
    private Integer size;
}
