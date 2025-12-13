package com.i4o.dms.itldis.salesandpresales.sales.invoice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SA_INVOICE_MACHINE_DETAIL")
public class SalesInvoiceMachine {

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
	    @JoinColumn(name = "machine_inventory_id",updatable=false)
	    private MachineInventory machineInventory;

	    @ManyToOne
	    @JoinColumn(name = "delivery_challan_id",updatable=false)
	    private DeliveryChallan deliveryChallan;

	    @ManyToOne
	    @JoinColumn(name = "sales_invoice_id",updatable=false)
	    @JsonBackReference
	    private SalesInvoice salesInvoice;
	    
	    private Long csbId;
}
