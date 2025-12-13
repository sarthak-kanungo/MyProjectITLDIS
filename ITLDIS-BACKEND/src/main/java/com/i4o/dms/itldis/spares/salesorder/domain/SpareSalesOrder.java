package com.i4o.dms.itldis.spares.salesorder.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
@Entity
@Table(name = "SP_SALES_ORDER")
public class SpareSalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Sales Order Number is mandatory")
    @Column(length = 21,unique = true)
    private String salesOrderNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    @NotNull(message = "Sales Order is mandatory")
    private Date salesOrderDate = new Date();

    @NotNull(message = "Customer Type is mandatory")
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

    @Min(value = 6,message = "Pin code should be 6 digit")
    @NotNull(message = "Pin code is mandatory")
    private Integer pinCode;
    
    private Long pinId;
    
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
   // @NotNull(message = "contact Number is mandatory")
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
    private Double totalSalesOrderAmount;

    @ManyToOne
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;
/*
    @ManyToOne
    @JoinColumn(name = "prospect_master_id")
    private ProspectMaster prospectMaster;*/

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "spare_quotation_id")
    private SpareQuotation spareQuotation;

    @OneToMany(mappedBy = "spareSalesOrder",cascade = {CascadeType.ALL})
    @JsonManagedReference
    @NotNull(message = "Part Details is mandatory")
    private List<SpareSalesOrderPartDetail> spareSalesOrderPartDetailList;

    @NotNull(message = "draft Flag is mandatory")
    private Boolean draftFlag;

   @Column(updatable=false)
    private Long createdBy;
   
   @Column(updatable=false)
   	private Date createdDate;
    private Long lastModifiedBy;
    
    private Date lastModifiedDate;
    private Long branchId;

    @Column(length = 50)
    @NotNull(message = "status is mandatory")
    private String status;

    @ManyToOne
    @JoinColumn(name = "party_master_id")
    private PartyMaster partyMaster;

    @ManyToOne
    @JoinColumn(name = "co_dealer_id")
    private DealerMaster coDealer;

    
    @Transient
    private String deleteParts;
}
