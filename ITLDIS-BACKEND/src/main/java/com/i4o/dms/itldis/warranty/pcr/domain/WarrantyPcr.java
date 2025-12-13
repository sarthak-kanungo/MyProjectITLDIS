package com.i4o.dms.itldis.warranty.pcr.domain;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Entity
@Getter
@Setter
@Table(name="wa_pcr")
public class WarrantyPcr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 21,unique = true)
    private String pcrNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private Date pcrDate=new Date();

    private String status;

    @NotNull(message = "crop can't be null")
    private String crop;

    @NotNull(message = "crop  condition can't be null")
    private String cropCondition;

    @NotNull(message = "failure type can't be null")
    private String failureType;

    @NotNull(message = "soil type can't be null")
    private String soilType;
    
    private String delayReason;

    @NotNull(message = "field condition can't be null")
    private String fieldCondition;

    @Column(columnDefinition = "boolean default false")
    private Boolean repeatFailureFlag;

    private Integer failureCount=0;

    @NotNull(message = "dealer observation can't be null")
    private String dealerObservation;

    @NotNull(message = "action take can't be null")
    private String actionTaken;

    private String dealerRemarks;

    private String kaiRemarks;
    @Column(updatable=false)
    private String specialApvRemarks;
    @Column(columnDefinition = "boolean default false")
    private Boolean draftFlag;

    @ManyToOne
    @JoinColumn(name = "service_jobcode_id")
    private ServiceJobCard serviceJobCard;

    @OneToMany(mappedBy = "warrantyPcr",cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyPcrImplements> warrantyPcrImplements;

//    @OneToMany(mappedBy = "warrantyPcr",cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "requisition_item")
//    private List<SparePartRequisitionItem> sparePartRequisitionItem;
//
//    @Transient
//    private List<SparePartRequisitionItem> sparePartRequisitionItemList;

    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdDate = new Date();
    
    @Column(updatable=false)
    private Long branchId;

    private Long lastModifiedBy;
    
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "warrantyPcr",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyPcrPhotos> warrantyPcrPhotos;

    private String soldToDealer;
    private String serviceDealer;
    
    private Boolean allowVideoUpload = false;
    
    private String serviceDealerAddress;

    private Boolean goodwillFlag=false;
    @Transient
    private String wcrNo;
    @Transient
    private String mobileNumber;
    @Transient
    private String emailId;
    @Transient
    private String dealerCode;
    @Transient
    private String createWcr;
}
