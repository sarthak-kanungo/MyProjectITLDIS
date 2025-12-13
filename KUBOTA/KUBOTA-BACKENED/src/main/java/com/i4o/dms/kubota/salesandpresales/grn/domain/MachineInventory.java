package com.i4o.dms.kubota.salesandpresales.grn.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="machine_inventory_ledger")
public class MachineInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "allot_flag",columnDefinition = "bit default 0")
    private Boolean allotFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate = new Date();

    @ManyToOne
    @JoinColumn(name = "grn_id")
    private MachineGrn machineGrn;

    @ManyToOne
    @JoinColumn(name = "vin_id")
    private MachineVinMaster machineVinMaster;

}
