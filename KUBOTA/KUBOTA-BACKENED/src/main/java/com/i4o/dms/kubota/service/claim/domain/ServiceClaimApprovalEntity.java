package com.i4o.dms.kubota.service.claim.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SV_Claim_approval")
public class ServiceClaimApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceClaimId;

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

