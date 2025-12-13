package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.domain;

import javax.persistence.Column;
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
@Table(name = "SP_STOCK_CURRENT")
public class SpareStockCurrent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private Long branchId;
	
	private String itemNo;
	
	private Integer totalQty;
	
	private Double stockAmount;
	
	private Boolean isNonMoving;

}
