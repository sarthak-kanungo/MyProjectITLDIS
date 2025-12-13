package com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.service;

import com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaim;
import com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDescripancyClaimDtoClass;
import com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDescripancyClaimSaveDto;
import com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDiscrepancyClaimViewDto;

import java.util.List;

public interface MachineDescripancyClaimService {
    List<MachineDescripancyClaimDtoClass> getMachineDescripancyClaim(Long id);
    List<MachineDescripancyClaimDtoClass> getSavedMachineDescripancyComplaintDetails(Long id);
    MachineDiscrepancyClaim saveMachineDescripancyClaim(MachineDescripancyClaimSaveDto machineDescripancyClaimSaveDto);
    MachineDiscrepancyClaimViewDto getMachineDiscrepancyById(Long claimId);
}
