package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.SaleEnquiryDetailDto;
import com.i4o.dms.itldis.service.servicebooking.domain.ServiceBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="QA_CCE_CALL_HDR")
public class CustomerCareExeCallHdr {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	private Long dealerId;
	
	@Column(updatable=false)
	private Date callDate = new Date();
	
	@Column(updatable=false)
	private String callNo = "CALL/"+System.currentTimeMillis();

	
	private String callDetails;
	
    private Long salesEnquiryId;
    
    private Long serviceBookingId;
    
    private Long jcId;
    
    private Long dcId;
    
    private String startTime;
    
    private String endTime;
    
	@ManyToOne
	@JoinColumn(name = "type_of_call_id", referencedColumnName = "id")
	private CrmCustomerCallType callType;
	
	@ManyToOne
	@JoinColumn(name = "call_status_id", referencedColumnName = "id")
    private QaCrmCallStatus crmCallStatus;
	
	@Transient
	private SaleEnquiryDetailDto enquiryDetailDto;
	
	@Transient
	private List<QaCrmCceCallFeedback> callFeedback;
	
	@Transient
	private ServiceBooking serviceBooking;
	
	private Long customerMasterId;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Date modifiedDate;
	
	@Column(updatable=false)
	private Long createdBy;
	
	private Long modifiedBy;
	
//	private String customerType;
//	private Long pinId;
//	private Long vinId;
//	private Long customerMasterId;
//	@JsonProperty(value="contactPersonName")
//	private String customerName;
//	@JsonProperty(value="contactPersonMobile")
//	private String mobileNo;
//	@JsonProperty(value="relationWithCustomer")
//	private String relationship;
	
//	@JsonManagedReference
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="customerCareExeCallHdr")
//	private List<CustomerCareExeCallDtl> complaintDtlList; 
//	@Transient
//	private Map<String, Object> customerDetails;
//	@Transient
//	private Map<String, Object> enquiryDetails;
	
}
