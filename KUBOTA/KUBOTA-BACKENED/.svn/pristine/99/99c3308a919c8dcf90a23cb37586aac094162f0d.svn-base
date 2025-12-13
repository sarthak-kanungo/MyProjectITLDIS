package com.i4o.dms.kubota.service.pdi.domain;

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

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineInventory;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SV_PDI")
public class ServicePdi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 21, unique = true)
    private String pdiNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @NotNull(message = "pdi date is mandatory")
    @Column(updatable=false)
    private Date pdiDate = new Date();

    @NotNull(message = "Ok Flag is mandatory")
    private Boolean okFlag;

    @ManyToOne
    @JoinColumn(name = "machine_inventory_id")
    private MachineInventory machineInventory;

    private String remarks;

    @NotNull(message = "Draft flag is mandatory")
    private Boolean draftFlag = false;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @Column(updatable=false)
    private Long createdBy;

    @Column(updatable=false)
    private Date createdDate = new Date();
    
    private Long modifiedBy;

    private Date modifiedDate;
    
    @Transient
    private Set<ServicePdiChassisCheckpointInfo> servicePdiChassisCheckpointInfoSet;

}
