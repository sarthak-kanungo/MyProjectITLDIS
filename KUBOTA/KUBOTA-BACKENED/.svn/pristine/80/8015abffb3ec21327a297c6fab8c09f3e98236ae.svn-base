package com.i4o.dms.kubota.masters.dealermaster.createdealeruser.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
@Table(name="ADM_MENU_ROLE_USER_HDR")
public class DealerRoleFunction implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(updatable=false)
	private Long loginUserId;
	
	@Column(updatable=false)
	private Long roleId;
	
	@Column(updatable=true)
	 private Character isactive;
	
	@Column(updatable=false)
	private Date createdDate = new Date();
	
	private Long lastModifiedBy;

	private Date lastModifiedDate;


}
