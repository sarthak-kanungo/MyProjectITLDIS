package com.i4o.dms.itldis.service.pdc.domain;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="SV_PDC")
public class ServicePdc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String pdcNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @NotNull
    @Column(updatable=false)
    private Date pdcDate=new Date();

    @NotNull
    private Boolean okFlag;

    @ManyToOne
    @JoinColumn(name="machine_inventory_id",updatable=false)
    private MachineInventory machineInventory;

    private String remarks;

    @NotNull
    private Boolean draftFlag=false;

    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Long createdBy;

    @Column(updatable=false)
    private Date createdDate;
    
    private Long modifiedBy;

    private Date modifiedDate;
    

    @Transient
    Set<ServicePdcChassisCheckpointInfo> servicePdcChassisCheckpointInfoSet;

}
