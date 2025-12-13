package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.domain;

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
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.BranchDepotMaster;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BRANCH_TRANSFER_INDENT")
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class SPBranchTransferIndent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 21)
	private String reqNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	private Date reqDate = new Date();
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean draftFlag = false;
	
	@ManyToOne
	@JoinColumn(name = "req_from_branch", referencedColumnName = "id")
	private BranchDepotMaster reqFromBranch;
	
	@ManyToOne
	@JoinColumn(name = "req_by", referencedColumnName = "id")
	private DealerEmployeeMaster reqBy;
	
	@ManyToOne
	@JoinColumn(name = "req_to_branch", referencedColumnName = "id")
	private BranchDepotMaster reqToBranch;
	
	private String status;
	
	private String remarks;
	
	@OneToMany(mappedBy = "transferIndent", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SPBranchTransferIndentItem> indentItems; 
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
	
}
