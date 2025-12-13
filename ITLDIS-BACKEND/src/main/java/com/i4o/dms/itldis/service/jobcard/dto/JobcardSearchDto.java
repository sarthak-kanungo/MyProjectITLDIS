package com.i4o.dms.itldis.service.jobcard.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobcardSearchDto {

    public String jobCardNo;
    public String chassisNo;
    public String engineNo;
    public String csbNo;
    public String bookingNo;
    public String jobCardDateFrom;
    public String jobCardDateTo;
    public String customerName;
    public String model;
    public String registrationNo;
    public String mobileNo;
    public String serviceCategory;
    public String placeOfService;
    public String activityType;
    public String activityNo;
    public Integer page;
    public Integer size;
    public String status;
    public String isFor;
    public Long dealerId;
    public String fromMachineInDate;
    public String toMachineInDate;
    public String product;
    public String series;
    public String subModel;
    public String variant;
    public Long  hierId;
    public Long branch;
    public String partNo;
}
