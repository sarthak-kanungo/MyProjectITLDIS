package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MachineDiscrepancyClaimViewDto {
    private Long claimId;

    private String claimNumber;

    private String claimStatus;

    private String remarks;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date claimDate;

    private Long createdBy;
    private boolean draftMode;
    List<MachineDescripancyClaimDtoClass> machineDescripancyClaim;
    
	private List<Map<String, Object>> approvalDetails;
}
