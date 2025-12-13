package com.i4o.dms.itldis.accpac.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class AccpacDmsSparesPoItemSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    private Long quantity;

    private Integer invoiceQuantity = 0;

    @NotNull(message = "Unit Price is mandatory")
    private Double unitPrice;

    @NotNull
    private Double baseAmount;

    @NotNull
    private Double cgstPercent;
    @NotNull
    private Double sgstPercent;
    @NotNull
    private Double igstPercent;

    @NotNull
    private Double cgstAmount;

    @NotNull
    private Double sgstAmount;

    @NotNull
    private Double igstAmount;

    @NotNull
    private Double totalAmount;
    private String status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "spare_purchase_order_id")
    //@JsonBackReference
    private DmsAccpacSparesPo dmsAccpacSparesPo;

    @NotNull
    @Size(max=30)
    private String accpacOrderNo;

    @NotNull
    @Size(max = 30)
    private String partItemNo;

    @NotNull
    @Size(max=100)
    private String partDescription;

    @NotNull
    @Size(max = 30)
    private String kaiSupplyPartNo;

  //  @NotNull
  //  @Size(max = 22)
    private String dmsPoNumber;
}
