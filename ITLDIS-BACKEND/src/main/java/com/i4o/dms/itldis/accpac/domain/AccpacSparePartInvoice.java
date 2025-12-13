package com.i4o.dms.itldis.accpac.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ACCPAC_SPARES_INVOICE")
public class AccpacSparePartInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    private String accpacInvoiceNo;

    @NotNull(message = "Invoice Date may not be null")
    private Date accpacInvoiceDate;

    @NotNull(message = "Invoice value may not be null")
    @DecimalMin(value = "1.0")
    private Double invoiceValue;

    @Min(value = 1)
    private Integer sentBoxesQty;

    @DecimalMin(value = "1.0")
    private Double receiptValue;

    @NotNull(message = "Dealer Code may not be null")
    @Size(min = 1)
    private String dealerCode;

    @NotNull(message = "Transport mode may not be null")
    private String transportMode;

    @NotNull(message = "Transporter may not be null")
    private String transporter;

    @NotNull
    private Date syncDate = new Date();

    /////required in transit report
    private Date expectedDeliveryDate;

    private String lrNo;

    private Boolean grnDoneFlag = false;

   // @NotNull(message = "PO No may not be null")
    //@Size(min = 1)
    //private String purchaseOrderNo;

}
