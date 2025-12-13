package com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MachineDescripancyComplaintDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String itemNo;

    @Column(length = 50)
    private String itemDescription;

    @Column(length = 15)
    private Integer quantity;

    @Column(length = 50)
    private String remarks;

    @Column(length = 50)
    private String type;

    @Column(length = 15)
    private Integer approvedQuantity;

    @Column(length = 15)
    private Integer claimQuantity;

    @Column(length = 15)
    private Integer approvedClaimQuantity;

    @ManyToOne
    @JoinColumn(name = "machine_descripancy_complaint_id")
    @JsonBackReference
    private MachineDescripancyComplaint machineDescripancyComplaint;

}
