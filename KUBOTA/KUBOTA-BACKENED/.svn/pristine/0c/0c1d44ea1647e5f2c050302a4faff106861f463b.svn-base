package com.i4o.dms.kubota.accpac.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name="ACCPAC_SPARES_INVOICE_ITEM")
public class AccpacSparePartInvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Acc Pac Spare Invoice No may not be null")
   // @Size(min = 1)
    private String invoiceNo;
//
    @ManyToOne
    @JoinColumn(name="accpac_invoice_id")
    private AccpacSparePartInvoice accpacSparePartInvoice;

    @NotNull(message = "Accpac Order No may not be null")
    @Size(min = 1)
    private String accpacOrderNo;

    @NotNull(message = "Item No may not be null")
    @Size(min = 1)
    private String itemNo;

    @NotNull(message = "Item Description may not be null")
    @Size(min = 1)
    private String itemDescription;

    @NotNull(message = "HSN code may not be null")
    @Size(min = 1)
    private String hsnCode;

    @DecimalMin(value = "1.0")
    private Double unitPrice;

    private Double spmgst;

    private Double spegst;

    private Double spmrp;

    @Min(value = 1)
    private Integer invoiceQty;

    @DecimalMin(value = "1.0")
    private Double assessableValue;

    @DecimalMin(value = "1.0")
    private Double discount;

    @DecimalMin(value = "1.0")
    private Double cgstPercent;

    @DecimalMin(value = "1.0")
    private Double cgstAmount;

    @DecimalMin(value = "1.0")
    private Double sgstPercent;

    @DecimalMin(value = "1.0")
    private Double sgstAmount;

    @DecimalMin(value = "1.0")
    private Double igstPercent;

    @DecimalMin(value = "1.0")
    private Double igstAmount;

    private Boolean grnDoneFlag = false;

}
