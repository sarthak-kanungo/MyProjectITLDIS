package com.i4o.dms.itldis.spares.invoice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "SP_SALES_INVOICE_ITEM")
public class SparesInvoicePartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable=false)
    private String hsnCode;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotNull(message = "unitPrice is mandatory")
    @Column(name = "unit_price", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double unitPrice;
    private Double spmrp;
    @NotNull(message = "Amount is mandatory")
    @Column(name = "amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double amount;

    @Column(name = "discount_percent", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double discountPercent;

    @Column(name = "discount_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double discountAmount;

    @Column(name = "net_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double netAmount;

    @Column(name = "cgst_percent", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double cgstPercent;

    @Column(name = "cgst_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double cgstAmount;

    @Column(name = "sgst_percent", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double sgstPercent;

    @Column(name = "sgst_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double sgstAmount;

    @Column(name = "igst_percent", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double igstPercent;

    @Column(name = "igst_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double igstAmount;

    @NotNull(message = "Sub total is mandatory")
    @Column(name = "sub_total", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double subTotal;

    @NotNull(message = "Gst Amount is mandatory")
    @Column(name = "gst_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double gstAmount;

    @NotNull(message = "Total Amount is mandatory")
    @Column(name = "total_amount", columnDefinition = "Decimal(12,2) default '00.00'")
    private Double totalAmount;

    @NotNull(message = "spare part master is  mandatory")
    @Column(updatable=false)
    private String sparePartMasterId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "spares_invoice_id")
    private SparesInvoice sparesInvoice;
    
    //@NotNull(message = "pick list id is mandatory")
    @Column(updatable=false)
    private Long picklistDtlId;
}