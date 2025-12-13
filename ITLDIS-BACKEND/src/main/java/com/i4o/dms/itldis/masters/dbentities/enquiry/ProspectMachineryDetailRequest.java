package com.i4o.dms.itldis.masters.dbentities.enquiry;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@JsonIgnoreProperties(value = {"isactive","customerMaster"},allowSetters = true)
@Table(name = "SA_CUST_REQ_MACHINERY_DTL")
public class ProspectMachineryDetailRequest implements Serializable
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cust_req_id",nullable = false)
    @JsonBackReference
    private CustomerMasterRequest customerMaster;
    @Column(name="cust_machinery_dtl_id")
    private Long custMachineryDtlId;

}
