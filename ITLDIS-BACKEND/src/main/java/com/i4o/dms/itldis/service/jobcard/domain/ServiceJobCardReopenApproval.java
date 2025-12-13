package com.i4o.dms.itldis.service.jobcard.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SV_JOBCARD_REOPEN_APPROVAL")
public class ServiceJobCardReopenApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name="service_job_card_id")
    private ServiceJobCard serviceJobCard;*/
    
    private Long serviceJobCardId;
    
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
