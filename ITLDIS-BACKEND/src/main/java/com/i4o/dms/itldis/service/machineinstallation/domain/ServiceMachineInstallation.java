package com.i4o.dms.itldis.service.machineinstallation.domain;


import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.itldis.masters.dbentities.service.domain.ServiceMtMachineInstallationType;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SV_INSTALLATION")
@JsonIgnoreProperties(value = {"createdBy", "createdOn", "modifiedBy", "modifiedOn"})
public class ServiceMachineInstallation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String installationNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date installationDate=new Date();

    @Column(length = 50)
    private String customerRepName;

    @Column(length = 50)
    private String representativeType;

    @Column(length = 50)
    private String csbNumber;

    @NotNull
    private Boolean draftFlag=false;

    @ManyToOne
    @JoinColumn(name="machine_inventory_id")
    private MachineInventory machineInventory;

    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Long createdBy;

    @Transient
    Set<ServiceDiChassisCheckpointInfo> serviceDiChassisCheckpointInfo;

    @Transient
    Set<ServiceFiChassisCheckpointInfo> serviceFiChassisCheckpointInfo;

    @ManyToOne
    @JoinColumn(name="machine_installation_type_id")
    private ServiceMtMachineInstallationType machineInstallationType;

    @ManyToOne
    @JoinColumn(name="service_staff_id")
    private DealerEmployeeMaster dealerEmployee;

    //photo upload
    @OneToMany(mappedBy = "serviceMachineInstallationId")
    private List<ServiceInstallationPhotos> serviceInstallationPhotosList;

    @Transient
    private List<MultipartFile> multipartFileList;

    @Column(updatable=false)
    private Date createdOn = new Date();
    
    private Long modifiedBy;
    
    private Date modifiedOn;
}
