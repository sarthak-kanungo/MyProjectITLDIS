package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SM_activity_proposal_approval")
public class MarketingActivityProposalApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long marketingActivityProposalId;

    private Integer approverLevelSeq;
    
    private BigInteger designationLevelId;
    
    private Integer grpSeqNo;
    
    private Character isfinalapprovalstatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    private String approvalStatus;

    private Long hoUserId;
    
    private Character rejectedFlag;

    private String remark;
}
