package com.i4o.dms.itldis.spares.salesorder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_SALES_ORDER_PART_DETAIL")
public class SpareSalesOrderPartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hsnCode;

    @Min(value = 1,message = "Minimum quantity should be one")
    @NotNull(message = "Quantity is mandatory")
    private Double quantity;

    private Double issueQuantity;

   /* @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocationMaster;*/

    @NotNull(message = "Unit Price is mandatory")
    private Double unitPrice;

    private Double amount;

    private Double discountPercent;

    private Double discountAmount;

    private Double netAmount;

    private Double cgstPercent;

    private Double cgstAmount;

    private Double sgstPercent;

    private Double sgstAmount;

    private Double igstPercent;

    private Double igstAmount;

    @NotNull(message = "Sub Total is mandatory")
    private Double subTotal;

    @NotNull(message = "Gst Amount is mandatory")
    private Double gstAmount;

    @NotNull(message = "Total invoice amount is mandatory")
    private Double totalInvoiceAmount;

    @ManyToOne
    @JoinColumn(name = "spare_sales_order_id")
    @JsonBackReference
    private SpareSalesOrder spareSalesOrder;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    /*private Boolean invoicedFlag=false;*/

    /*@NotNull(message = "deleted flag is mandatory")
    private Boolean deletedFlag=false;*/

    /*private Boolean pickingSlipFlag=false;*/
    
    private Double backQuantity;
    
    private String remarks;
}



