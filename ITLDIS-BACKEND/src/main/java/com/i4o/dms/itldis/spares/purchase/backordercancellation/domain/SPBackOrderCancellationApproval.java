package com.i4o.dms.itldis.spares.purchase.backordercancellation.domain;

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
@Table(name = "SP_BACK_ORDER_CANCELLATION_APPROVAL")
public class SPBackOrderCancellationApproval {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bocId;

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
    
    private Integer approvedQuantity;

}
