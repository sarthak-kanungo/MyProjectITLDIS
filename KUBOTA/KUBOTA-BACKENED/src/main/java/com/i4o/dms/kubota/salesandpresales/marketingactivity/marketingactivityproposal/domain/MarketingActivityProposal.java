package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.domain.MarketingActivityTypeHead;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate","dealerEmployeeMaster","dealerMaster","conversionTypeActivityProposals"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "SM_activity_proposal")
public class MarketingActivityProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityProposalId;

    @Column(length = 50)
    private String activityNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date activityCreationDate=new Date();

    @NotBlank(message = "marketing activity purpose can`t be blank")
    @NotNull
    @Column(length = 50)
    private String activityPurpose;

    @Column(name="activity_category_id")
    private Integer activityCategory;
//    @ManyToOne
//    @JoinColumn(name = "purpose_id")
//    private SourcePurpose sourcePurpose;

    //@NotBlank(message = "marketing activity type can`t be blank")
    @NotNull
    @Column(name="activity_type_id")
    private Integer activityType;
    
    @Transient
    private String activityTypeName;

//    @ManyToOne
//    @JoinColumn(name = "source_master_id")
//    private EnquirySourceMaster enquirySourceMaster;

    @NotBlank(message = "location can`t be blank")
    @NotNull
    @Column(length = 50)
    private String location;

    @Column(length = 50)
    private String activityStatus="Waiting for approval";

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fromDate;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date toDate;

    @NotNull
    @Column(length = 10)
    private Integer numberOfDays;

    @NotNull
    @Column(length = 15)
    private double proposedBudget;

    private Double approvedAmount;

    private Double actualClaimAmount;	//actual_claim_amount

    private Double approvedClaimAmount;

    @NotNull
    @Column(length = 15)
    private double maxAllowedBudget;

    @Column(name="claimable_amount")
    private Double claimableAmount;
    
    //@NotEmpty
    @OneToMany(mappedBy = "marketingActivityProposal",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ActivityHead> activityHeads;

//    @OneToMany(mappedBy = "marketingActivityProposal",cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "conversion_type_activity_proposal")
//    private List<ConversionTypeActivityProposal> conversionTypeActivityProposals;

    @ManyToMany()
    @JoinTable(name = "SM_activity_proposal_enquiry_mapping",
            joinColumns = @JoinColumn(name = "marketing_activity_id", referencedColumnName = "activityProposalId"),
            inverseJoinColumns = @JoinColumn(name = "enquiry_id", referencedColumnName = "id"))
    private List<Enquiry> enquiries;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastModifiedDate=new Date();

    /*@ManyToOne
    @JoinColumn(name = "created_by")
    private DealerEmployeeMaster dealerEmployeeMaster;*/
    
    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;




}
