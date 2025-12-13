package com.i4o.dms.kubota.warranty.deliverychallan.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface WarrantyDeliveryChallanViewDto {

    Long getId();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getDcDate();
    Long getBranchId();
    String getDcNo();
    String getTransporterName();
    String getDocketNumber();
    String getNumberOfBox();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getDispatchDate();
}
