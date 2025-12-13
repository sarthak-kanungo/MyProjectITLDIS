package com.i4o.dms.itldis.accpac.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class DmsAccpacSparesPoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotNull(message = "Unit Price is mandatory")
    private Double unitPrice;

    @NotNull
    private Double baseAmount;

    @NotNull(message = "Gst Percent is mandatory")
    private Double gstPercent;

    @NotNull
    private Double gstValue;

    @NotNull
    private Double totalAmount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "spare_purchase_order_id")
    //@JsonBackReference
    private DmsAccpacSparesPo dmsAccpacSparesPo;

    @NotNull
    @Size(max = 30)
    private String partItemNo;
    @NotNull
    @Size(max=100)
    private String partDescription;
}