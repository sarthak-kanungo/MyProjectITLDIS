package com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.CustomerCareExeCallDtl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="QA_TOLL_FREE_HDR")
public class TollFreeHdr {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	private Long dealerId;
	
	@Transient
	private String dealerCode;
	
	@Column(updatable=false)
	private Date callDate = new Date();
	
	@Column(updatable=false)
	private String callNo = "CALL/"+System.currentTimeMillis();
	
	private String customerType;
	
	private Long pinId;
	
	private Long vinId;
	private String enquiry;
	private Long customerMasterId;
	
	@JsonProperty(value="contactPersonName")
	private String customerName;
	@JsonProperty(value="contactPersonMobile")
	private String mobileNo;
	@JsonProperty(value="relationWithCustomer")
	private String relationship;
	
	private String additionalComment;
	
	@JsonProperty("smstextoCustomer")
	private String smsText;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="tollFreeCallHdr")
	private List<TollFreeDtl> complaintDtlList; 
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="tollFreeCallHdr")
	private List<TollFreeFileUplaod> fileUploadList; 
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Date modifiedDate;
	
	@Column(updatable=false)
	private Long createdBy;
	
	private Long modifiedBy;
	
	@Transient
	private Map<String, Object> customerDetails;
	@Transient
	private Map<String, Object> enquiryDetails;
}
