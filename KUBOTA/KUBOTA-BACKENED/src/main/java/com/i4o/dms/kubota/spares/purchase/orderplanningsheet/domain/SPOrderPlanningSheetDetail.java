package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain;

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
@Table(name = "SP_ORDER_PLANNING_SHEET_DTL")
public class SPOrderPlanningSheetDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private SPOrderPlanningSheet planningSheet;
	
	@ManyToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;
	
	private Integer reorderQty;
	
	private Integer dealerStock;
	
	private Integer kaiBackOrder;
	
	private Integer transitFromKai;
	
	private Integer l12mSales;
	
	private Integer suggestedOrderQty;
	
	private Integer actualOrderQty;
	
	private Double orderValue;
	
	private float unitPrice;
	
	private float gstPercent;	

}
