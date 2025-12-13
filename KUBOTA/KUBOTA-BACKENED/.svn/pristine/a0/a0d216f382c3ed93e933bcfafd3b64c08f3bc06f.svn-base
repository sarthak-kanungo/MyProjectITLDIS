package com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MachineDescripancyClaimDtoClass {
    private String complaintNumber;
    private String complaintStatus;
    private Boolean draftMode;
    private Long complaintId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date complaintDate;
    private String itemNo;
    private String itemDescription;
    private String chassisNo;
    private List<MachineDescripancyComplaintDetail> machineDescripancyComplaintDetails;
}
