package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","claimNumber","claimDate","claimStatus","totalClaimQuantity","totalApprovedQuantity","remarks"})
public interface ResponseSearchDto {
    Long getId();
    String getAction();
    String getClaimNumber();
    String getClaimDate();
    String getClaimStatus();
    Integer getTotalClaimQuantity();
    Integer getTotalApprovedQuantity();
    String getRemarks();
    @JsonIgnore
    Long getRecordCount();
}
