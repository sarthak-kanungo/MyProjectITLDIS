package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto;

import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MachineDescripancyClaimSaveListDto {
    private Long complaintId;
    private List<MachineDescripancyComplaintDetail> machineDescripancyComplaintDetails;

}
