package com.i4o.dms.itldis.warranty.pcr.dto;

public interface JobCardPartDto {


    Long getSparePartRequisitionId();
    String getPartNo();
    String getDescription();
    Integer getClaimQuantity();
    String getFailureDescription();

}
