package com.i4o.dms.kubota.spares.quotation.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.areamaster.model.PinCode;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.BranchDepotMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SP_QUOTATION")
public class SpareQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Spare Quotation Number is mandatory")
    @Column(length = 21,unique = true)
    private String quotationNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @NotNull(message = "Spare Quotation Date is mandatory")
    private Date quotationDate = new Date();

    @NotNull(message = "customer Type is mandatory")
    @Column(length = 50)
    private String customerType;

    @Column(length = 100)
    @NotNull(message = "customer Name is mandatory")
    private String customerName;

    @Column(length = 50)
    @NotNull(message = "customer Address1 is mandatory")
    private String customerAddress1;

    @Column(length = 50)
    private String customerAddress2;

    @Min(6)
    @NotNull(message = "Pin code is mandatory")
    private Integer pinCode;

    @NotNull(message = "Post Office is mandatory")
    @Column(length = 100)
    private String postOffice;

    @Column(length = 50)
    @NotNull(message = "Village is mandatory")
    private String village;

    @Column(length = 50)
    @NotNull(message = "Tehsil is mandatory")
    private String tehsil;

    @Column(length = 50)
    @NotNull(message = "District is mandatory")
    private String district;

    @Column(length = 50)
    @NotNull(message = "State is mandatory")
    private String state;

    @Column(length = 50)
    @NotNull(message = "country is mandatory")
    private String country;

    @Column(length = 15)
    //@NotNull(message = "contact Number is mandatory")
    private String contactNumber;

    @Column(length = 50)
    private String discountType;

    private Double discountRate;

    private Double totalDiscountValue;

    @NotNull(message = "total Basic Value is mandatory")
    private Double totalBasicValue;

    @NotNull(message = "total tax Value is mandatory")
    private Double totalTaxValue;

    @NotNull(message = "total Sale order Amount is mandatory")
    private Double totalQuotationAmount;

    @ManyToOne
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;
/*
    @ManyToOne
    @JoinColumn(name = "prospect_master_id")
    private ProspectMaster prospectMaster;*/

    @OneToMany(mappedBy = "spareQuotation", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<SpareQuotationPartDetail> quotationPartDetail;

    @NotNull(message = "draft Flag is mandatory")
    private Boolean draftFlag;

    
    @JoinColumn(name = "created_by", updatable = false)//createdby
    @NotNull(message = "Created By is mandatory")
    private Long createdBy;

    
    @JoinColumn(name = "last_modified_by")//lastModifiedBy
    @NotNull(message = "Last Modified By is mandatory")
    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @NotNull(message = "Dealer master is mandatory")
    private BranchDepotMaster branchDepotMaster;

    @NotNull(message = "Quotation status is mandatory")
    private String quotationStatus;

    @ManyToOne
    @JoinColumn(name = "party_master_id")
    private PartyMaster partyMaster;
    
//    @ManyToOne
//    @JoinColumn(name = "pin_id")
//    private PinCode pinId;
    private Long pinId;
    
    private Date createdDate = new Date();

    private Date lastModifiedDate = new Date();
}
