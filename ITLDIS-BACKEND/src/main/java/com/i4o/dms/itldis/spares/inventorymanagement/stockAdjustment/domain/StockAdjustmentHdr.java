package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SP_STKADJ_HDR")
public class StockAdjustmentHdr {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stkadjHdrId;
	@Column(updatable=false)
	private Long branchId;
	@Column(updatable=false)
	private String adjustmentNumber = "ADJ/"+ System.currentTimeMillis();;
	@Column(updatable=false)
	private Date adjustmentDate;
	
	private String adjustmentStatus;
	
	@Column(updatable=false)
	private Date createdDate;
	@Column(updatable=false)
	private Long createdBy;
	
	private Date modifiedDate;
	
	private Long modifiedBy;

	@OneToMany(mappedBy="stockAdjustmentHdr",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonManagedReference("spareStockAdjustment")
	private List<StockAdjustmentDtl> stockAdjustmentDtls;
}
