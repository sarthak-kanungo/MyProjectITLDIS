/**
 * 
 */
package com.i4o.dms.kubota.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dnsh87
 *
 */
@Entity
@Getter
@Setter
@Table(name = "SYS_LOOKUP")
public class SystemLookUpEntity{
	
	@Id
	@Column(name = "lookup_id")
	private Long lookupId;
	private String lookuptypecode;
	private String lookupval;
	@JsonProperty("statusName")
	private String lookuptext;
	private Integer displayorder;
	private Date createddate;
	private String createdby;
	private Date modifieddate;
	private String modifiedby;
	@Type(type="yes_no")
	private Boolean isactive;
	
}
