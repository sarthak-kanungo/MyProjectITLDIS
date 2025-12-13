package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain;

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
@Table(name = "SP_PART_DISCREPANCY_CLAIM_APPROVAL")
public class SpPartDiscrepancyClaimApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long spDiscrHdrId;

    private Integer approverLevelSeq;
    
    private BigInteger designationLevelId;
    
    private Integer grpSeqNo;

    private String approvalStatus;
    
    private Character isFinalApprovalStatus;

    private Character rejectedFlag;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    private Long hoUserId;
    
    private String remark;
	
}
