package com.i4o.dms.itldis.warranty.goodwill.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodwillSearch {
    String goodwillNo;
    String pcrNo;
    String goodwillStatus;
    String goodwillFromDate;
    String goodwillToDate;
    Long page;
    Long size;
    String jobcardNo;
    String machineModel;
    String chassisNo;
    String failureType;
    String mobileNo;
    String registrationNo;
    String jobCardFromDate;
    String jobCardToDate;
    Long dealerId;
    Long branch;
    Long state;
    Long orgHierarchyId;
}
