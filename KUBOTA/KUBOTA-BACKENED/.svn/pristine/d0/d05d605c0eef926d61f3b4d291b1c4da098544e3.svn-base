package com.i4o.dms.kubota.warranty.hotlinereport.domain;

import java.util.Date;
import java.util.List;
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
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerDepotMapping;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import com.i4o.dms.kubota.masters.dbentities.user.SalesMasterFormDepotAddress;
import com.i4o.dms.kubota.masters.dbentities.warranty.WarrantyMtFailureType;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;


import lombok.Getter;
import lombok.Setter;

/**
 *@author suraj.gaur 
 */
@Getter
@Setter
@Entity
@Table(name="WA_HTLR_HDR")
public class WarrantyHotlineReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String htlrNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    
    private Date htlrDate;
    
    //private Long plantMstId;
    @ManyToOne
    @JoinColumn(name = "PLANT_MST_ID", referencedColumnName = "id")
    private WarrantyHotlineReportPlantMaster hotlinePlantMaster;
    
    //private Long depoId;
    @ManyToOne
    @JoinColumn(name = "DEPO_ID", referencedColumnName = "id")
    private SalesMasterFormDepotAddress dealerDepotMapping;
    
    //private Long deptId;
    @ManyToOne
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "id")
    private KubotaDepartmentMaster departmentMaster;
    
    @ManyToOne
    @JoinColumn(name = "INCHARGE_ID", referencedColumnName = "id")
    private KubotaEmployeeMaster employeeMaster;
    
    //private Long failureTypeId;
    @ManyToOne
    @JoinColumn(name = "FAILURE_TYPE_ID", referencedColumnName = "id")
    private WarrantyHotlineReportFailureMaster failureType;

    @NotNull(message = "failure date in WarrantyHotlineReport can't be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date failureDate;
    
    //private Long condFailId;
    @ManyToOne
    @JoinColumn(name = "COND_FAIL_ID", referencedColumnName = "id")
    private WarrantyHotlineConditionFailureMaster conditionFailureMaster;
    
    @OneToMany(mappedBy = "warrantyHotlineReport", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyHotlineReportPartsDetail> hotlineReportPartsDetails;
    
    @OneToMany(mappedBy = "warrantyHotlineReport", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyHotlineReportMachineDetails> hotlineReportMachineDetails;
    
    @OneToMany(mappedBy = "warrantyHotlineReport",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyHotlineReportAttachment> hotlineReportAttachments;

    @NotNull(message = "status WarrantyHotlineReport can't be null")
    private String status = "";
    
    @NotNull(message = "respose from vendore in WarrantyHotlineReport can't be null")
    private String vendorResponse = "";
    
    private String complaint;
    
    private String remarks;
    
    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
    
    @Transient
    private String vendorResponseTemp;
    
}
