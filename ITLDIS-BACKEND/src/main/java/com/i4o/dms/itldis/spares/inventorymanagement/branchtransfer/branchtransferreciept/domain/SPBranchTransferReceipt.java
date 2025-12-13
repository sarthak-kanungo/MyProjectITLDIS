package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.domain;

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
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.domain.SPBranchTransferIssue;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BRANCH_TRANSFER_RECEIPT")
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class SPBranchTransferReceipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String receiptNo = "DRA/" + ThreadLocalRandom.current().nextInt(1000) + "/" + System.currentTimeMillis();
	
	private Date receiptDate = new Date();
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean draftFlag = false;
	
	@Column(length = 50)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "issued_id", referencedColumnName = "id")
	private SPBranchTransferIssue transferIssue; 
	
	@ManyToOne
	@JoinColumn(name = "received_by", referencedColumnName = "id")
	private DealerEmployeeMaster receivedBy;
	
	@ManyToOne
	@JoinColumn(name = "issuing_branch_id", referencedColumnName = "id")
	private BranchDepotMaster issuingBranchId;
	
	@ManyToOne
	@JoinColumn(name = "receiving_branch_id", referencedColumnName = "id")
	private BranchDepotMaster receivingBranchId;
	
	@OneToMany(mappedBy = "transferReceipt", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SPBranchTransferReceiptItem> receiptItems;	
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
}
