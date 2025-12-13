package com.i4o.dms.itldis.warranty.hotlinereport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action", "hotlineReportNo", "depot", "departmentName", "createdDate"})
public interface WarrantyHotlineSearchResponceDto {
	
	String getHotlineReportNo();
    String getDepot();
    String getCreatedDate();
    String getDepartmentName();
    String getAction();
    @JsonIgnore
    Long getTotalCount();
    
}
