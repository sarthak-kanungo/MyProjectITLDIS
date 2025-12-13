package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.DesignationLevelMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SYS_APPROVAL_FLOW_MST")
public class SystemApprovalFlowMaster {
	
	@Id
	@Column(name = "approval_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "transaction_name")
	private String transactionName;
	
	@Column(name = "approver_level_seq")
	private Integer approverLevelSeq;
	
	@ManyToOne
	@JoinColumn(name = "designation_level_id", referencedColumnName = "id")
	private DesignationLevelMaster designationLevelMaster;
	
	@Column(name = "grp_seq_no")
	private Integer grpSeqNo;
	
	@Column(name = "isFinalApprovalStatus")
	private Character isFinalApprovalStatus;

}
