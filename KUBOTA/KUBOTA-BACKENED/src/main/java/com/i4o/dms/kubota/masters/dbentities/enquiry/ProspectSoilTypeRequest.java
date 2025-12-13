package com.i4o.dms.kubota.masters.dbentities.enquiry;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMasterRequest;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"customerMaster"},allowSetters = true)
@Table(name ="SA_CUST_REQ_SOIL_TYPE")
public class ProspectSoilTypeRequest implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -222987753884182576L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String soilName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cust_req_id",nullable = false)
    @JsonBackReference
    private CustomerMasterRequest customerMaster;

}
