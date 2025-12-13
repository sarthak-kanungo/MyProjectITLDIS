package com.i4o.dms.kubota.spares.picklist.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="SP_SALES_PICKLIST_HDR")
public class PickList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long picklistHdrId;
	
	@Column(updatable=false)
	private Long branchId;
	
	@Column(updatable=false)
	private Long spareSalesOrderId;
	
	@Column(unique=true, updatable=false)
	private String picklistNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	@Column(updatable=false)
	private Date picklistDate = new Date();
	
	@Column(updatable=false)
	private String status = "Open";
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate = new Date();
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;

	@JsonManagedReference
	@OneToMany(mappedBy="pickList", cascade=CascadeType.ALL)
	private List<PickListItemDtl> itemDetails;
}