package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface MachineDescripancyClaimDto {
    Long getComplaintId();
    String getComplaintNumber();
    String getComplaintStatus();
    Boolean getDraftMode();
    List<MachineDescripancyComplaintDetail> getMachineDescripancyComplaintDetails();
    interface MachineDescripancyComplaintDetail{
        Long getId();
        String getItemNo();
        String getItemDescription();
        int getQuantity();
        String getRemarks();
        String getType();
        int getApprovedQuantity();
    }

    GrnMachineDetails getGrnMachineDetails();
    interface GrnMachineDetails{

        String getChassisNo();
        String getItemNo();
        String getItemDescription();

    }
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getComplaintDate();
}
