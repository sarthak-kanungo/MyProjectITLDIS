package com.i4o.dms.kubota.warranty.logsheet.domain;

import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineVinMaster;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="wa_logsheet")
public class WarrantyLogsheet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =21,unique = true)
    private String logsheetNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();


    private Date logsheetDate=new Date();

    @Column(columnDefinition = "boolean default false")
    private Boolean draftFlag=false;

    @NotNull(message = "status can't be null")
    private String status;

    @NotNull(message = "logsheet can't be null")
    @Column(updatable=false)
    private String logsheetType;

    @ManyToOne
    @JoinColumn(name = "vin_id", updatable=false)
    private MachineVinMaster machineInventory;

    @ManyToOne
    @JoinColumn(name = "customer_master_id", updatable=false)
    private CustomerMaster customerMaster;

    @ManyToOne
    @JoinColumn(name = "service_jobcard_id", updatable=false)
    private ServiceJobCard serviceJobCard;

    @JsonProperty("failureDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfFailure;

    private Double hours;

    private String crop;

    private String cropCondition;

    private String failureType;

    private String soilType;

    private String fieldCondition;

    private String probableCause;

    private String resultOfConfirmation;

    private Boolean repeatFailure=false;

    private Integer noOfTimes;

    @NotNull(message = "Tentative action can't be null")
    private String tentativeAction;

    @NotNull(message = "Remarks can't be null")
    private String remarks;

    @OneToMany(mappedBy = "warrantyLogsheet",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LogsheetImplement> logsheetImplements;

    @OneToMany(mappedBy = "warrantyLogsheet",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LogsheetFailurePartInfo> logsheetFailurePartInfo=new ArrayList<>();

    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdDate = new Date();

    private Long lastModifiedBy;

    private Date lastModifiedDate;

    @OneToMany(mappedBy ="warrantyLogsheet",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<WarrantyLogsheetPhotos> warrantyLogsheetPhotosList;

    private String qaRemarks;	//Suraj 11-10-2022
    
    private String serviceRemarks;	//Suraj 11-10-2022



}
