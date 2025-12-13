package com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "INCENTIVE_SCHEME_MASTER_ATTACHMENT")
public class IncentiveSchemeMasterAttachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long incentiveSchemeId;
	
	private String fileName;
	
	private String fileType;

}
