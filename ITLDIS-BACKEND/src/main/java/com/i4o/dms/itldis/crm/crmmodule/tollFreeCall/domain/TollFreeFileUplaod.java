package com.i4o.dms.itldis.crm.crmmodule.tollFreeCall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="QA_TOLL_FREE_FILE_UPLOAD")
public class TollFreeFileUplaod {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TOLL_FREE_HDR_ID")
	private TollFreeHdr tollFreeCallHdr;
	
	@Column(updatable=false)
	private Date createdDate = new Date();
	
	private Boolean deleteFlag = false;
	
	private String fileName;
	
	@JsonProperty("name")
	private String originalName;
	
	private String contentType;
	
	private Date lastModifiedDate;
}
