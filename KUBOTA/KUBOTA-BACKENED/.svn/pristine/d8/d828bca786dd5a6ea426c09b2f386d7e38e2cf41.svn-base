package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain;

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

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_ORDER_PLANNING_SHEET")
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class SPOrderPlanningSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false)
	private Long dealerId;
	
	@Column(unique = true, length = 21)
	private String opsNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	private Date orderDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "order_type_id", referencedColumnName = "id")
	private SpareOrderPlanningOrderType planningOrderType;

	@Column(columnDefinition = "Set draftFlag default false")
    private Boolean draftFlag = false;
	
	private float reorderQtyMonths;
	
	private float suggestedOrderQty;
	
	@ManyToOne
    @JoinColumn(name = "logic_id", referencedColumnName = "id")
    private SPOrderPlanningSheetLogic opSheetLogic;
	
	private Double totalOrderValue;
	
	private String status;
	
	@OneToMany(mappedBy = "planningSheet", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SPOrderPlanningSheetDetail> orderPlanningSheetDetails;
	
	private Long purchaseOrderId;
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
	
}
