package com.i4o.dms.kubota.salesandpresales.sales.invoice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SA_INVOICE_CANCEL_REQUEST")
public class SalesInvoiceCancelRequest {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date requestDate;
	
	private Long invoiceId;
	
	private Date invCancelDate;
	
	private String invCancelRemark;
	
	private String invCancellationReason;
	
	private String invCancellationOtherReason; 
	
	private String invCancellationType;
	
	private String cancelApprovalStatus;
	
	private Long createdBy;
	
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
	
}
