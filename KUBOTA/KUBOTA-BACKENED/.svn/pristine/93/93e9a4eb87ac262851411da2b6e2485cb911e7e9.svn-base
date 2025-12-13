package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="SA_DELIVERY_CANCEL_REQUEST")
public class DeliveryChallanCancelRequest {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date requestDate;
	
	private Long deliveryChallanId;
	
	private Date dcCancelDate;
	
	private String dcCancelRemark;
	
	private String dcCancellationReason;
	
	private String dcCancellationOtherReason; 
	
	private String dcCancellationType;
	
	private String cancelApprovalStatus;
	
	private Long createdBy;
	
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
	
}
