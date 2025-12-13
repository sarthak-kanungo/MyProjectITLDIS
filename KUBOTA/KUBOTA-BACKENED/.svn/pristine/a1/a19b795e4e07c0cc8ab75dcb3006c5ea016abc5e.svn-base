package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.StoreMaster;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.domain.SPBranchTransferIndent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BRANCH_TRANSFER_ISSUE_ITEM")
public class SPBranchTransferIssueItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "issue_id", referencedColumnName = "id")
	private SPBranchTransferIssue transferIssue;
	
	@ManyToOne
    @JoinColumn(name = "indent_id", referencedColumnName = "id")
    private SPBranchTransferIndent transferIndent;
	
	@ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;
	
	private Integer currentStockQty;
	
	private Integer reqQty;
	
	@ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private StoreMaster storeMaster;

    @ManyToOne
    @JoinColumn(name = "bin_id", referencedColumnName = "id")
    private BinLocationMaster binLocationMaster;
	
	private Integer issuedQty;
	
	private Integer pendingQty;
	
	private Double itemMrp;
	
	private Double itemValue;
	
}
