package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.domain;

import java.util.Date;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.BranchDepotMaster;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BRANCH_TRANSFER_ISSUE")
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class SPBranchTransferIssue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 21)
	private String issueNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	private Date issueDate = new Date();
	
	@Column(columnDefinition = "Setted draftFlag default false")
    private Boolean draftFlag = false;
	
	@Column(columnDefinition = "It accept Draft and Submitted as values")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "issued_by_branch_id", referencedColumnName = "id")
	private BranchDepotMaster byBranchMaster;
	
	@ManyToOne
	@JoinColumn(name = "issued_by_id", referencedColumnName = "id")
	private DealerEmployeeMaster employeeMaster;
	
	@ManyToOne
	@JoinColumn(name = "issued_to_branch_id", referencedColumnName = "id")
	private BranchDepotMaster toBranchMaster;
	
	@OneToMany(mappedBy = "transferIssue", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SPBranchTransferIssueItem> issueItems; 
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
}
