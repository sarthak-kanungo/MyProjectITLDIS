package com.i4o.dms.itldis.masters.dbentities.enquiry;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "SA_CUSTOMER_MACHINERY_DTL")
public class ProspectMachineryDetail implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1921119301222798935L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private  String brand;
    @Column(length = 60)
    private  String model;
    @Column(length = 10)
    private  String yearOfPurchase;

    private Character isactive='Y';
    private Date lastupdatedon = new Date();
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ProspectMaster prospectMaster;*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_master_id",nullable = false)
    private CustomerMaster customerMaster;




}
