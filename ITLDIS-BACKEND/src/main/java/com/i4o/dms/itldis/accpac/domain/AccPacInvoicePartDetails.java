package com.i4o.dms.itldis.accpac.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "acc_pac_invoice_machine_detail")
public class AccPacInvoicePartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemNo;

    private String itemDescription;

    private Integer invoiceQuantity;

    private String chassisNo;

    private String engineNo;

    private Double unitPrice;

    private Double totalValue;

    private Boolean mrcDoneFlag = false;

    @ManyToOne
    @JoinColumn(name = "acc_pac_invoice_id")
    private AccPacInvoice accPacInvoice;

    private String accpacInvoiceNo;

    private Double gstAmount;

    private Double assessableAmount;

}
