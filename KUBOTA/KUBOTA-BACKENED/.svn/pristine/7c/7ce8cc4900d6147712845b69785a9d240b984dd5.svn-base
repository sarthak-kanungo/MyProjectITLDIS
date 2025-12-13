package com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.domain;

import java.util.Date;

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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="QA_TOLL_FREE_CALL_DTL")
public class TollFreeDtl {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TOLL_FREE_HDR_ID")
	private TollFreeHdr tollFreeCallHdr;
	
	@Column(updatable=false)
	private Date createdDate = new Date();
	private String complaintNumber = "COMP/"+System.currentTimeMillis();
	private String department;
	@Transient
	private String assignTo;
	@JsonProperty("assignToId")
	private Long hoUserId;
	private String complaintType;
	private String description;
	private String actionTaken;
	
	private Date lastModifiedDate;
}
