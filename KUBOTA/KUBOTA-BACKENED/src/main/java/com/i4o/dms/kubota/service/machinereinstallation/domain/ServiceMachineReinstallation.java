package com.i4o.dms.kubota.service.machinereinstallation.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.products.domain.Series;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="SV_REINSTALLATION")
public class ServiceMachineReinstallation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String reInstallationNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date reInstallationDate=new Date();

    @NotNull
    private Boolean draftFlag=false;

    private Long branchId;

    @ManyToOne
    @JoinColumn(name="machine_series_id")
    private Series machineSeries;

    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdOn;
    
    private Long modifiedBy;
    
    private Date modifiedOn;
    
    @ManyToOne
    @JoinColumn(name="service_staff_id")
    private DealerEmployeeMaster dealerEmployee;

    @OneToMany(mappedBy = "serviceMachineReinstallation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceReinstallationChassisInfo> serviceReinstallationChassisInfo;

    @Transient
    Set<ServiceReinstallationChassisCheckpointInfo> serviceRiChassisCheckpointInfo;
}
