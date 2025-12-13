package com.i4o.dms.itldis.masters.dbentities.enquiry;

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
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"lastupdatedon","isactive","customerMaster"},allowSetters = true)
@Table(name ="SA_CUSTOMER_SOIL_TYPE")
public class ProspectSoilType implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -222987753884182576L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String soilName;
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ProspectMaster prospectMaster;*/
    private Character isactive='Y';
    private Date lastupdatedon = new Date();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_master_id",nullable = false)
    private CustomerMaster customerMaster;

}
