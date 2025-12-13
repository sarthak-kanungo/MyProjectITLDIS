package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.domain;

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
@Table(name="SP_PURCHASE_ORDER_APPROVAL")
public class SparePOApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sparesOrderId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    private String approvalStatus;

    private Long hoUserId;

    private Character  isfinalapprovalstatus;

    private String remark;
    
    private Character rejectedFlag;
    
    private Integer grpSeqNo;
    
    private BigInteger designationLevelId;
    
    private Integer approverLevelSeq; 
    
}
