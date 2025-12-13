package com.i4o.dms.kubota.service.psc.domain;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineInventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
@Table(name="SV_PSC")
public class ServicePsc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String pscNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    private Date pscDate=new Date();

    @ManyToOne
    @JoinColumn(name="machine_inventory_id", updatable=false)
    private MachineInventory machineInventory;

    @NotNull
    private Boolean draftFlag=false;

    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Long createdBy;

    @Column(updatable=false)
    private Date createdDate = new Date();
    
    private Long modifiedBy;

    private Date modifiedDate;
    
    @Transient
    Set<ServicePscChassisCheckpointInfo> servicePscChassisCheckpointInfo;

}


