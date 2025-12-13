package com.i4o.dms.itldis.spares.purchase.orderplanningsheet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mahesh.kumar
 */

@Getter
@Setter
@Entity
@Table(name = "SP_ORDER_PLANNING_LOGIC")
public class SPOrderPlanningSheetLogic {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String logic;
    
    private String status;

}
