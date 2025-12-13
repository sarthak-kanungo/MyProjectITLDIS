package com.i4o.dms.kubota.salesandpresales.sales.invoice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.products.domain.MachineMaster;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SA_INVOICE_ITEM_DETAIL")
public class SalesInvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rate;

    private Double discount;

    private Integer quantity;

    private Double gstPercent;

    private Double gstAmount;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "machine_master_id",updatable=false)
    private MachineMaster machineMaster;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id",updatable=false)
    private DeliveryChallan deliveryChallan;

    @ManyToOne
    @JoinColumn(name = "sales_invoice_id",updatable=false)
    @JsonBackReference
    private SalesInvoice salesInvoice;
}
