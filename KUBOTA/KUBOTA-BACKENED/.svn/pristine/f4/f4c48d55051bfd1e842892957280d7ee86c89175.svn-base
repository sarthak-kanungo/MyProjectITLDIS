package com.i4o.dms.kubota.accpac.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
public class DmsAccpacSalesPoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String itemNo;

    @Column(length = 500)
    private String itemDescription;

    @Column(length = 50)
    private String variant;

    @Column(length = 50)
    private String colour;

    @Column(length = 10)
    private int quantity;

    @Column(length = 10)
    private Integer accpacStockQuantity;

    private Integer invoiceQuantity;

    private String accpacOrderNo;

    private Date accpacOrderDate;

    private String status;

    @Column(length = 10)
    private String accpacLocationCode;


    @Column(length = 15)
    private double unitPrice;

    @Column(length = 15)
    private double amount;

  /*  @Column(name="gst_amount")
    private Double igst;*/

    private Double discountPercentage;

    private Double discountAmount;

    private String discountType;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    //@JsonBackReference
    private DmsAccpacSalesPo dmsAccpacSalesPo;
}
