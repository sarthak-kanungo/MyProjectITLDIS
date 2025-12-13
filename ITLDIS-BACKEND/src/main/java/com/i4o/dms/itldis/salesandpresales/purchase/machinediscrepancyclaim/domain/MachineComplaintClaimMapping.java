package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MachineComplaintClaimMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "machine_descripancy_complaint_id")
    @JsonBackReference
    private MachineDescripancyComplaint machineDescripancyComplaint;

    @ManyToOne
    @JoinColumn(name = "machine_discrepancy_claim_id")
    @JsonBackReference
    private MachineDiscrepancyClaim machineDiscrepancyClaim;




}
