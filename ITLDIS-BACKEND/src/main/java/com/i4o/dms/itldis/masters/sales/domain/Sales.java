package com.i4o.dms.itldis.masters.sales.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="SA_CUSTOMER_VEHICLE_DTL")
public class Sales implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 699752246020279950L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chassisNo;
    private String registrationNo;
    private String modelCode;
    private String modelDesc;
    @ManyToOne
    @JoinColumn(name="customer_master_id",nullable = false)
    @JsonBackReference
    private CustomerMaster customerMaster;

}
