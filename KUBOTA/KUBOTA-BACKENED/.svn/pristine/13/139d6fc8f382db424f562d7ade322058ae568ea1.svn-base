package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BRANCH_TRANSFER_INDENT_ITEM")
public class SPBranchTransferIndentItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "indent_id", referencedColumnName = "id")
	private SPBranchTransferIndent transferIndent;
	
	@ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;
	
	private Long reqQty;
	
	@Column(columnDefinition = "Requesting Branch Available Stock Quantity")
	private Integer reqBranchStockQty; 
	
	@Column(columnDefinition = "Supplying Branch Available Stock Quantity")
	private Integer supBranchStockQty;
	
}
