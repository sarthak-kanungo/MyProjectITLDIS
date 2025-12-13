package com.i4o.dms.kubota.spares.invoice.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SP_SALES_INVOICE")
public class SparesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Invoice Number is mandatory")
    @Column(length = 25, unique = true)
    private String invoiceNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    private Date invoiceDate = new Date();
    
    @Column(updatable=false)
    private Date createdDate = new Date();

    @Column(length = 100)
    private String transportMode;

    @Column(length = 100)
    private String transporter;

    @Column(length = 100)
    private String lrNo;
    
    private Date lrDate;
    
    private String paymentType;

    @NotNull(message = "total Base Amount is mandatory")
    @Column(name = "total_base_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double totalBaseAmount = 0.0;

    private Double tcsPercent = 0.0;
    
    private Double tcsAmount;
    		
    @NotNull(message = "total tax value is mandatory")
    @Column(name = "total_tax_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double totalTaxAmount = 0.0;

    @NotNull(message = "total Sale order Amount is mandatory")
    @Column(name = "total_invoice_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double totalInvoiceAmount = 0.0;

    @OneToMany(mappedBy="sparesInvoice", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<SparesInvoicePartDetail> itemDetails;
    
    @OneToMany(mappedBy="sparesInvoice", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<SparesInvoiceLabourDetail> labourDetails;
    
    @OneToMany(mappedBy="sparesInvoice", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<SparesInvoiceOutsideChargeDetail> outsideChargeDetails;

    @Column(updatable=false)
    private Long sparesSalesOrderId;

    @NotNull(message = "Sales Type is mandatory")
    private String salesType;

    @Column(updatable=false)
    private Long serviceJobcardId;
    
    @Column(updatable=false)
    private Long warrantyWcrId;

    @Column(updatable=false)
    private Long createdBy;

    private Long lastModifiedBy;
    
    private Date lastModifiedDate;
    
    @Column(updatable=false)
    private Long branchId;

    @Column(length = 100)
    private String customerName;

    @Column(length = 50)
    private String customerAddress1;

    @Column(length = 50)
    private String customerAddress2;

    private Integer pinCode;

    @Column(length = 100)
    private String postOffice;

    @Column(length = 50)
    private String village;

    @Column(length = 50)
    private String tehsil;

    @Column(length = 50)
    private String district;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String country;

    @Column(length = 15)
    private String contactNumber;
    
    private Boolean draftFlag=false; 
}
