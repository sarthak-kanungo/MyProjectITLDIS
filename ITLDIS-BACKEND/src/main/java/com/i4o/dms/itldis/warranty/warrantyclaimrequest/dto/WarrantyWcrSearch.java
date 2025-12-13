package com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarrantyWcrSearch {

    String wcrNo;
    String wcrType;
    String wcrStatus;
    String pcrNo;
    String jobCardNo;
    String chassisNo;
    String wcrFromDate;
    String wcrToDate;
    String machineModel;
    String failureType;
    String customerMobileNo;
    String registrationNo;
    String jobCardFromDate;
    String jobCardToDate;
    String pcrFromDate;
    String pcrToDate;
    Long page;
    Long size;
    
    String product;
    String variant;
    String series;
    String subModel;
    Long orgHierarchyId;
    Long branch;
    Long dealerId;
    Long state;
    
    Long finalStatus;	//Suraj--27-09-2022
}
