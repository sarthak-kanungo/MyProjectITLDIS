package com.i4o.dms.kubota.salesandpresales.sales.invoice.domain;


import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.masters.dbentities.enquiry.BankMaster;
import com.i4o.dms.kubota.masters.dbentities.salespresales.sales.deliverychallan.InsuranceCompanyMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate", "createdBy", "customerMaster", "hibernateLazyInitializer", "dealerMaster", "dealerEmployeeMaster"}, allowSetters = true)
@Table(name="SA_INVOICE")
public class SalesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable=false)
    private Long branchId;
    
    @NotNull
    private String invoiceType;

    @Column(unique=true,length = 21)
    private String invoiceNumber = "INV/" + System.currentTimeMillis();

    // @NotNull
    private String invoiceStatus="Invoiced" ;

    //@NotNull
    @Column(updatable=false)
    private Date invoiceDate = new Date();

    //db driven
    private String invoiceCancellationType;

    //db driven
    private String invoiceCancellationReason;

    //private String brand;

   // private String model;

    //db driven
    private String otherReason;

    @Column(length = 1000)
    private String remarks;

    @NotNull
    private Double totalBasicAmount;

    @NotNull
    private Double totalGstAmount;

    private Double others;

    @NotNull
    private Double totalInvoiceAmount;

    private Double rtoCharges;

    private Double insuranceCharges;

   /* @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private CustomerMaster customerMaster;*/

    @ManyToOne
    @JoinColumn(name = "financer_id")
    private BankMaster bankMaster;

    @ManyToOne
    @JoinColumn(name = "insurance_master_id")
    private InsuranceCompanyMaster insuranceCompanyMaster;

    @OneToMany(mappedBy = "salesInvoice", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<SalesInvoiceItem> salesInvoiceItems;
    
    @OneToMany(mappedBy = "salesInvoice", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<SalesInvoiceMachine> salesInvoiceMachines;

    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    private DealerEmployeeMaster dealerEmployeeMaster;

    @JsonIgnore
    private Long lastModifiedBy;
    @JsonIgnore
    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdDate;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date policyStartDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date policyExpiryDate;

}
