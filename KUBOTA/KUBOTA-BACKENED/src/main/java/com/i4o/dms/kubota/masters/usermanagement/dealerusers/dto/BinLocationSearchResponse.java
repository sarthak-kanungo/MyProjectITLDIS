package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "branchId", "storeId", "binLocation"})
public interface BinLocationSearchResponse {

	Long getId();

	Long getBranchId();

    Long getStoreId();
    
    String getBinLocation();
}
