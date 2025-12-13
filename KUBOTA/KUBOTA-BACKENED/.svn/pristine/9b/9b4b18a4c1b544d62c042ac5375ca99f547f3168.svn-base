package com.i4o.dms.kubota.spares.picklist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="SP_SALES_PICKLIST_DTL")
public class PickListItemDtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long picklistDtlId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="picklist_hdr_id")
	private PickList pickList;
	
	@Column(updatable=false)
	private Long spareSalesOrderPartId;
	
	@Column(updatable=false)
	private String pickedItemNo;
	
	@Column(updatable=false)
	private Long binLocationId;
	
	@Column(updatable=false)
	private Integer issueQty;
	
	private Integer totalReturnQty;
	
	private Integer finalIssueQty;
	
	@Column(updatable=false)
	private Double unitPrice;
	
	@Column(updatable=false)
	private Double spegst;
	
	@Column(updatable=false)
	private Double spmgst;
	
	@Column(updatable=false)
	private Double spmrp;
	
	@Transient
	private Long storeId;

	@Transient
	private Integer returnQty;
	//private Character invoicedFlag = 'N';
	
}