package com.i4o.dms.itldis.service.jobcard.domain;


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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtCategory;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtServiceTypeInfo;
import com.i4o.dms.itldis.masters.service.serviceactivityproposal.domain.ServiceMtActivityType;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineVinMaster;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.itldis.service.servicebooking.domain.ServiceBooking;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.warranty.logsheet.domain.WarrantyLogsheet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SV_JOBCARD")
public class ServiceJobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "job card number can't be blank")
    @Column(length = 21,unique = true)
    private String jobCardNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    private Date jobCardDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfFailure;

    private String closeRemark;
    
    @ManyToOne
    @JoinColumn(name = "service_category_id")
    private ServiceMtCategory serviceCategory;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceMtServiceTypeInfo serviceType;

    @NotNull
    @NotBlank(message = "place of service can't not be null")
    private String placeOfService;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date taskDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date taskTime;
    
    private Date machineOutDateTime;
    
    @Transient
    private String outDateTime;

    @Column(updatable=false)
    private Long createdBy;

    @Column(updatable=false)
    private Long branchId;

    @Column(length = 40)
    private String registrationNumber;

    @NotNull
    private Boolean draftFlag=false;

    @Column(name = "job_card_ok_flag")
    @NotNull(message = "flag can't be null")
    Boolean jobCardOkFlag=false;

    @ManyToOne
    @JoinColumn(name = "serviceRepresentative_id")
    private DealerEmployeeMaster serviceRepresentative;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private DealerEmployeeMaster mechanic;

    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;

    private String customerName;

    private String mobileNumber;

    private String alternateNumber;

    @JsonProperty("address")
    private String customerAddress;

    @OneToMany(mappedBy = "serviceJobCard",cascade = CascadeType.ALL)
    @JsonManagedReference("sparePartRequisitionItem")
    @Where(clause="delete_flag=false")

     List<SparePartRequisitionItem> sparePartRequisitionItem;


    private String customerConcern;
    private String mechanicObservation;


    @OneToMany(mappedBy ="serviceJobCard",cascade = CascadeType.ALL)
    @JsonManagedReference("serviceJobcardPhotos")
    private List<ServiceJobcardPhotos> serviceJobcardPhotos;

    @OneToMany(mappedBy = "serviceJobCard",cascade = CascadeType.ALL)
    @JsonManagedReference("outsideJobCharge")
    @Where(clause="delete_flag=false")
    private List<OutsideJobCharge> outsideJobCharge;

    @OneToMany(mappedBy = "serviceJobCard",cascade = CascadeType.ALL)
    @JsonManagedReference("labourCharge")
    @Where(clause="delete_flag=false")
    private List<LabourJobCharges> labourCharge;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ServiceMtActivityType serviceMtActivityType;
//
    @ManyToOne
    @JoinColumn(name = "service_activity_proposal_id")
    private ServiceActivityProposal serviceActivityProposal;

    private Boolean invoicedFlag=false;

/*    private Boolean preinvoiceFlag=false;
*/
    private Boolean jobcardCloseFlag=false;


    private Double meterChangeHour;

    private Double currentHour;

    private Double totalHour;
    private Double previousHour;
    private String status;

    @Column(length =200)
    private String finalActionTaken;
    @Column(length =200)
    private String suggestionToCustomer;
    
    private Date lastModifiedDate;
    
    private Double estimatedAmount;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date estimatedCompletionDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date installationDate;

    private String soldDealer;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date closedDate;

    @ManyToOne
    @JoinColumn(name = "service_booking_id")
    private ServiceBooking serviceBooking;

    private Boolean pcrRaiseFlag=false;


    @Column(columnDefinition ="boolean default false",name ="part_issue_flag")
    private Boolean partIssueFlag=false;

    @Transient
    String serviceDealer;
    @Transient
    String serviceDealerCity;
    @Transient
    private String pcrNo;

    @Transient
    private Boolean pcrApprovedFlag;
    @Transient
    private Boolean goodwillRaised;
    @Transient
    private Boolean goodwillRequired;
    @Transient
    private Boolean goodwillApproved;
    @Transient
    private String gwNo;
    @Transient
    private Boolean allowVideoUpload;
    @Transient
    private Boolean isLatest;
    @ManyToOne
    @JoinColumn(name = "vin_id")
    private MachineVinMaster machineInventory;

    private String cancelRemarks;
 
    private Date cancelDate;
    
    @Column(updatable = false)
    private Date createdOn = new Date();
    /*
    @Column(updatable = false)
    private Boolean invoiceFlag = false;*/
    
    private String closeDelayReason;
    
    @Transient
    private String warrantyEndDate;
    @Transient
    private Double totalWarrantyHour;
    
    @OneToMany(mappedBy ="serviceJobCard",cascade = CascadeType.ALL)	//Suraj-24-04-2023
    @JsonManagedReference	//Suraj-24-04-2023
    private List<ServiceJobcardRetroMapping> jobcardRetroMappings;	//Suraj-24-04-2023
    
}
