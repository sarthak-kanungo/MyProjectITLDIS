package com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="WA_WCR_APPROVAL")
public class WarrantyWcrApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long warrantyWcrId;
    
    private Integer approverLevelSeq;
    
    private BigInteger designationLevelId;
    
    private Integer grpSeqNo;
    
    private String approvalStatus;
    
    private Character isfinalapprovalstatus;
    
    private Character rejectedFlag;
    
    private Date approvedDate;
    
    private Long hoUserId;
    
    private String remark;

}

