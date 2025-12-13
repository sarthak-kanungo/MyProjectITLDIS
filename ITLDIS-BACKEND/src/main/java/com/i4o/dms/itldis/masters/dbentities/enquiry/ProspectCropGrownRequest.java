package com.i4o.dms.itldis.masters.dbentities.enquiry;

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
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMasterRequest;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"customerMaster"},allowSetters = true)
@Table(name = "SA_CUST_REQ_CROP")
public class ProspectCropGrownRequest implements Serializable
{
	private static final long serialVersionUID = -5012658297208650348L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cust_req_id",nullable = false)
    @JsonBackReference
    private CustomerMasterRequest customerMaster;
}
