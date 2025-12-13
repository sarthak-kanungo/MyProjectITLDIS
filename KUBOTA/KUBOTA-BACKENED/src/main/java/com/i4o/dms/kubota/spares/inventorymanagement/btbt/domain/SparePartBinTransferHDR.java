package com.i4o.dms.kubota.spares.inventorymanagement.btbt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_PART_BIN_TRANSFER_HDR")
public class SparePartBinTransferHDR {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long binTransferId;
	
	private Long branchId;
	
	@Column(updatable = false)
	private String transferNumber = "TN/"+ System.currentTimeMillis();	
	
	@Column(updatable = false)
	private Long createdby;

	private Long modifiedby;
	
	@Temporal(TemporalType.DATE)
	private Date transferDate = new Date();	
	
	private String Remarks;
	
	@Column(updatable = false)
	private Date createddate = new Date();
	
	private Date modifieddate;
	
}
