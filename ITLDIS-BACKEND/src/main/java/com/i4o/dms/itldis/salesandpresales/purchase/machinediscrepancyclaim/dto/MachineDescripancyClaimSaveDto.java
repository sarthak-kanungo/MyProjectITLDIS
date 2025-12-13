package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MachineDescripancyClaimSaveDto {
    private Long createdBy;
    private boolean draftMode;
    private String remarks;
    private Long claimId;
    private Long dealerEmployeeId;
    List<MachineDescripancyClaimSaveListDto> machineDescripancyClaim;
}
