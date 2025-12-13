package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository;

import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineComplaintClaimMapping;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineComplaintClaimMappingRepo extends JpaRepository<MachineComplaintClaimMapping,Long> {

    List<MachineComplaintClaimMapping> findByMachineDiscrepancyClaim(MachineDiscrepancyClaim machineDiscrepancyClaim);

    @Query(value = "{call sp_salesandpresales_machine_complaint_claim_mapping_get_complaints()}",nativeQuery = true)
    List<MachineDescripancyComplaint> getMachineDiscrepancyComplaintsByClaim();

}
