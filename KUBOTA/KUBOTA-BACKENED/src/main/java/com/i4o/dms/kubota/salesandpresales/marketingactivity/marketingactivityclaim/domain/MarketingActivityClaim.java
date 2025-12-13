package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate","gstInvoiceDocument","activityHeads","claimHeads","dealerEmployeeMaster","dealerEmployeeId","marketingActivityProposalId","dealerMaster"}, allowSetters = true)
@Setter
@Getter
@Entity
@ToString
@Table(name = "SM_activity_claim")
public class MarketingActivityClaim  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @Column(length = 50)
    private String claimNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(length = 50)
    private String claimStatus="Claim Approval Pending";

    @NotNull
    @Column(length = 10)
    private int totalEnquiries;

    @NotNull
    @Column(length = 10)
    @JsonProperty("warmEnquiry")
    private int warmEnquiries;

    @NotNull
    @Column(length = 10)
    @JsonProperty("hotEnquiry")
    private int hotEnquiries;

    @NotNull
    @Column(length = 10)
    @JsonProperty("coldEnquiry")
    private int coldEnquiries;


    @NotNull
    @Column(length = 10)
    private double costPerEnquiry;

    @NotNull
    @Column(length = 10)
    private double costPerHotEnquiry;

    @NotNull
    @Column(length = 10)
    private int totalBookings;

    @NotNull
    @Column(length = 10)
    private double costPerBooking;

    @NotNull
    @Column(length = 10)
    private int totalRetails;

    @NotNull
    @Column(length = 10)
    private double costPerRetail;

    @Column(length = 50)
    private String activityEffectiveness;

    //Rename the field name as gst
    @Column(length = 50)
    private String gstInvoiceImage;

    @Transient
    @Column(length = 50)
    private MultipartFile gstInvoiceDocument;

    @Column(length = 10)
    private Double gstPercent;

    @Column(length = 10)
    private Double gstAmount;

    @Column(length = 10)
    private double totalClaimAmount;

    @Transient
    private List<Map<String,Object>> claimHeads;

    @Column(length = 10)
    private double totalApprovedAmount;

    @Column(length = 10)
    private double totalActualClaimAmount;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(updatable = false)
    private Date claimDate=new Date();

    @OneToMany(mappedBy="marketingActivityClaim", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<ActivityClaimHead> claimHeadList;
    
    @ManyToOne
    @JoinColumn(name = "marketing_activity_proposal_id")
    private MarketingActivityProposal marketingActivityProposal;

    @Transient
    private Long dealerEmployeeId;

    @Transient
    private Long marketingActivityProposalId;
    @Column(updatable=false)
    private Long createdBy;
    
    private Long modifiedBy;
    @Column(updatable=false)
    private Date createdOn = new Date();
    
    private Date modifiedOn;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

}
