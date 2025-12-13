package com.i4o.dms.itldis.service.activityclaim.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposalHeads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sv_activity_claim")
public class ServiceActivityClaim
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 21,unique = true)
    private String claimNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date claimDate=new Date();

    @Column(length = 50)
    private String claimStatus="Claim Approval Pending";

    @Column(length = 10,name ="total_claim_approved_amount")
    private double approvedAmount;

    @Column(length = 10)
    private double totalClaimAmount;

    @ManyToOne
    @JoinColumn(name = "service_activity_proposal_id")
    private ServiceActivityProposal serviceActivityProposal;

    @Transient
    private List<ServiceActivityProposalHeads> serviceActivityProposalHeads;

    @Transient
    private List<Map<String,Object>> claimHeads;

    @Transient
    private List<Map<String,Object>> subActivities;

    @Transient
    private Long serviceActivityProposalId;

    private Date modifiedOn;
    private Long modifiedBy;


    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdOn=new Date();

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

}
