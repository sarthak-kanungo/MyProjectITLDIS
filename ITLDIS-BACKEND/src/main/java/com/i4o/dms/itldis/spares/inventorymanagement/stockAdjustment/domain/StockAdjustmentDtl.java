package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SP_STKADJ_DTL")
public class StockAdjustmentDtl {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stkadjDtlId;
	
	@ManyToOne
	@JoinColumn(name="stkadj_hdr_id")
    @JsonBackReference("spareStockAdjustment")
	private StockAdjustmentHdr stockAdjustmentHdr;
	
	private Integer srNo;
	
	private String partNo;
	
	private Long storeId;
	@Transient
	private String store;
	
	private Long stockBinId;
	@Transient
	private String binLocation;
	
	private String adjustmentType;
	
	private BigDecimal mrp;
	
	private Integer qtyAdjusted;
	
	private BigDecimal increasedAmount;
	
	private BigDecimal decreasedAmount;   
	
	

}
