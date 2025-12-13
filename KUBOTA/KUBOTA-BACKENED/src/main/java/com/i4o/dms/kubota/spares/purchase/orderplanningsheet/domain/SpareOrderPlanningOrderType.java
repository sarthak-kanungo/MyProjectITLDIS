package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain;

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
@Table(name = "SP_ORDER_PLANNING_ORDER_TYPE")
public class SpareOrderPlanningOrderType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String orderType;
	
	private boolean activeFlag;

}
