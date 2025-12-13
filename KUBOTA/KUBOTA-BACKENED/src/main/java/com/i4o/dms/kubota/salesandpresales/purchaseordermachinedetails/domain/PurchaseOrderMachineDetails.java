package com.i4o.dms.kubota.salesandpresales.purchaseordermachinedetails.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.products.domain.MachineMaster;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SA_PURCHASE_ORDER_MACHINE_DTL")
public class PurchaseOrderMachineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    @JsonBackReference
    private PurchaseOrder purchaseOrder;

    @Column(length = 50)
    private String colour;

    @Column(length = 10)
    private Integer quantity;

    @Column(length = 10)
    private Integer accpacStockQuantity;

    @Column(length = 10)
    private String accpacLocationCode;

    @Column(length = 15)
    private Double unitPrice;

    @Column(name="delete_flag")
    private Boolean isDelete=false;

    @Column(length = 15)
    private Double amount;

    private Double igst;

    private Double discountPercentage;

    private Double discountAmount;

    private String discountType;

    @ManyToOne
    @JoinColumn(name="machine_master_id")
    private MachineMaster machineMaster;
}
