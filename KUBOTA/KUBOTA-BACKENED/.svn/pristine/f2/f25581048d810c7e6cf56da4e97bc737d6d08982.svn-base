package com.i4o.dms.kubota.masters.dbentities.enquiry;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"lastupdatedon","isactive","customerMaster"},allowSetters = true)
@Table(name = "SA_CUSTOMER_CROP")
public class ProspectCropGrown implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5012658297208650348L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropName;
    private Character isactive='Y';
    private Date lastupdatedon = new Date();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_master_id",nullable = false)
    private CustomerMaster customerMaster;
}
