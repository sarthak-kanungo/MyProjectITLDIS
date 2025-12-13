package com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;

import lombok.Getter;
import lombok.Setter;
/**
 * @author suraj.gaur
 */
@Entity
@Getter
@Setter
@Table(name = "incentive_scheme_master_detail")
@JsonIgnoreProperties(value = {"createdBy", "lastModifiedDate"}, allowSetters = true)
public class IncentiveSchemeMasterDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="incentive_scheme_id", referencedColumnName = "id", nullable = false)
    private IncentiveSchemeMaster incentiveSchemeMaster;

    @Column(name="t1_quantity")
    private String t1Quantity;
    
    @Column(name="t1_incentive_per_quantity")
    private String t1IncentivePerQuantity;
    
    @Column(name="t2_quantity")
    private String t2Quantity;
    
    @Column(name="t2_incentive_per_quantity")
    private String t2IncentivePerQuantity;
    
    @Column(name="t3_quantity")
    private String t3Quantity;
    
    @Column(name="t3_incentive_per_quantity")
    private String t3IncentivePerQuantity;
    
    @Column(name="t4_quantity")
    private String t4Quantity;
    
    @Column(name="t4_incentive_per_quantity")
    private String t4IncentivePerQuantity;
    
    @Column(name="t5_quantity")
    private String t5Quantity;
    
    @Column(name="t5_incentive_per_quantity")
    private String t5IncentivePerQuantity;
    
    private Integer maximumQuantity;

    @ManyToOne
    @JoinColumn(name="dealer_employee_id")
    private DealerEmployeeMaster dealerEmployee;
    
    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerMaster dealer;
    
    @Transient
    private String dealerCode;
    @Transient
    private String dealerEmployeeCode;
    @Transient
    private String errorMsg; 
    
}



